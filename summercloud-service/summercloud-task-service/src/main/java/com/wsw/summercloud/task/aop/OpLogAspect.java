package com.wsw.summercloud.task.aop;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsw.summercloud.common.annotation.OpLog;
import com.wsw.summercloud.common.enums.ModuleTypeEnum;
import com.wsw.summercloud.common.enums.OperationTypeEnum;
import com.wsw.summercloud.common.log.OpLogDTO;
import com.wsw.summercloud.common.utils.CompareUtils;
import com.wsw.summercloud.common.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description: 操作日志记录切面
 * @Author: wangsongwen
 * @Date: 2023/4/9 16:49
 */
@Slf4j
@Aspect
@Component
public class OpLogAspect {
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
            OpLogDTO opLogDTO = new OpLogDTO();
            opLogDTO.setOperateType(opLog.opType().getMessage());
            opLogDTO.setModuleType(opLog.type().getDes());
            if (opLog.opType() == OperationTypeEnum.UPDATE) {
                //过滤忽略字段，比较实体的变化字段
                String operateContent = CompareUtils.compareFields(oldValue, newValue, opLog.ignoreFields());
                //任务池消息通知配置日志处理
                if (opLog.type().getCode().equals(ModuleTypeEnum.RESOURCE.getCode()) && StrUtil.isNotBlank(operateContent)) {
                    //特殊处理
                }
                opLogDTO.setOperateContent(operateContent);
            } else if (opLog.opType() == OperationTypeEnum.ADD || opLog.opType() == OperationTypeEnum.DELETE) {
                opLogDTO.setOperateContent(opLog.opType().getMessage() + opLog.type().getDes());
            }
            //操作人员

            Object paramValue = getParamValue(pjp, opLog);
            opLogDTO.setModuleId(String.valueOf(paramValue));
            if (StrUtil.isNotBlank(opLogDTO.getOperateContent())) {
                log.info("操作日志写入成功! " + JSON.toJSONString(opLogDTO));
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
                String params = JSONObject.toJSONString(arg);
                String idString = JSONPath.read(params, opLog.typeId()).toString();
                Long id = Long.valueOf(idString);
                Class<IService> iServiceClass = opLog.serviceClass();
                IService iService = SpringUtils.getBean(iServiceClass);
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
                String params = JSONObject.toJSONString(arg);
                return JSONPath.read(params, opLog.moduleId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
