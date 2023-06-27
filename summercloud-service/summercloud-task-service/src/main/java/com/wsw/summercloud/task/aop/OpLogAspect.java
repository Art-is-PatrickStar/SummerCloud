package com.wsw.summercloud.task.aop;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsw.summercloud.api.basic.UserInfo;
import com.wsw.summercloud.common.annotation.OpLog;
import com.wsw.summercloud.common.enums.ModuleTypeEnum;
import com.wsw.summercloud.common.enums.OperationTypeEnum;
import com.wsw.summercloud.api.dto.OpLogDto;
import com.wsw.summercloud.common.utils.CompareUtil;
import com.wsw.summercloud.common.utils.SpringUtil;
import com.wsw.summercloud.common.utils.UserInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Description: 操作日志记录切面
 * @Author: wangsongwen
 * @Date: 2023/4/9 16:49
 */
@Slf4j
@Aspect
@Component
public class OpLogAspect {
    @Autowired
    private ObjectMapper objectMapper;

    @Pointcut("@annotation(com.wsw.summercloud.common.annotation.OpLog)")
    private void logPointCut() {
    }

    @Around("logPointCut()")
    public Object setOpLog(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null, oldValue = null, newValue = null;
        boolean isException = false;
        Throwable currentException = null;
        OpLog opLog = null;
        try {
            //通过连接点获取方法签名 被切入方法的所有信息
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            //获取被切入方法对象
            Method method = signature.getMethod();
            //获取方法上的注解
            opLog = method.getAnnotation(OpLog.class);
            if (opLog.opType() == OperationTypeEnum.UPDATE) {
                oldValue = getResult(pjp, opLog);
                result = pjp.proceed();
                newValue = getResult(pjp, opLog);
            } else if (opLog.opType() == OperationTypeEnum.ADD || opLog.opType() == OperationTypeEnum.DELETE) {
                result = pjp.proceed();
            }
        } catch (Throwable ex) {
            //执行异常则标识
            isException = true;
            //先记录异常，到方法最后再抛出
            currentException = ex;
        }
        try {
            //操作日志类
            OpLogDto opLogDTO = new OpLogDto();
            opLogDTO.setOperateType(opLog.opType().getMessage());
            opLogDTO.setModuleType(opLog.type().getDes());
            if (opLog.opType() == OperationTypeEnum.UPDATE) {
                //过滤忽略字段，比较实体的变化字段
                String operateContent = CompareUtil.compareFields(oldValue, newValue, opLog.ignoreFields());
                //任务池消息通知配置日志处理
                if (opLog.type().getCode().equals(ModuleTypeEnum.RESOURCE.getCode()) && StrUtil.isNotBlank(operateContent)) {
                    //特殊处理
                }
                opLogDTO.setOperateContent(operateContent);
            } else if (opLog.opType() == OperationTypeEnum.ADD || opLog.opType() == OperationTypeEnum.DELETE) {
                opLogDTO.setOperateContent(opLog.opType().getMessage() + opLog.type().getDes());
            }
            //操作人员
            UserInfo currentUserInfo = UserInfoUtil.getCurrentUserInfoThreadLocal();
            opLogDTO.setCreatedUser(currentUserInfo.getUserName());
            opLogDTO.setCreatedTime(new Date());
            Object paramValue = getParamValue(pjp, opLog);
            opLogDTO.setModuleId(String.valueOf(paramValue));
            if (StrUtil.isNotBlank(opLogDTO.getOperateContent())) {
                log.info("操作日志写入成功! " + objectMapper.writeValueAsString(opLogDTO));
            }
        } catch (Throwable ex) {
            log.error("操作日志写入失败! " + ex);
        }
        if (isException) {
            throw currentException;
        }
        return result;
    }

    private Object getResult(ProceedingJoinPoint pjp, OpLog opLog) {
        try {
            Object[] args = pjp.getArgs();
            if (args.length > 0) {
                Object arg = args[0];
                String params = objectMapper.writeValueAsString(arg);
                String idString = objectMapper.readTree(params).get(opLog.typeId()).asText();
                Long id = Long.valueOf(idString);
                Class<IService> iServiceClass = opLog.serviceClass();
                IService iService = SpringUtil.getBean(iServiceClass);
                return iService.getById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Object getParamValue(ProceedingJoinPoint pjp, OpLog opLog) {
        try {
            Object[] args = pjp.getArgs();
            if (args.length > 0) {
                Object arg = args[0];
                String params = objectMapper.writeValueAsString(arg);
                return objectMapper.readTree(params).get(opLog.moduleId()).asText();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
