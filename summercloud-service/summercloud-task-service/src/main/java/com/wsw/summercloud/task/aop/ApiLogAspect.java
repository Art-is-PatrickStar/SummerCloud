package com.wsw.summercloud.task.aop;

import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsw.summercloud.api.basic.UserInfo;
import com.wsw.summercloud.api.dto.ApiLogDto;
import com.wsw.summercloud.common.utils.UserInfoUtil;
import com.wsw.summercloud.task.service.impl.ApiLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/8 13:38
 */
@Slf4j
@Aspect
public class ApiLogAspect {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ApiLogService apiLogService;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    private void pointCut() {
    }

    @Around("pointCut()")
    public Object process(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = null;
        boolean isException = false;
        Throwable currentException = null;
        try {
            result = pjp.proceed();
        } catch (Throwable ex) {
            isException = true;
            currentException = ex;
        }
        try {
            long costTime = System.currentTimeMillis() - start;
            ApiLogDto apiLogDto = buildApiLog(pjp, costTime, isException, currentException, result);
            apiLogService.appendApiLog(apiLogDto);
        } catch (Throwable ex) {
            log.error("记录api日志异常", ex);
        }
        if (isException) {
            throw currentException;
        }
        return result;
    }

    private ApiLogDto buildApiLog(ProceedingJoinPoint pjp, long costTime, boolean isException, Throwable currentException, Object result) throws JsonProcessingException {
        //通过连接点获取方法签名 被切入方法的所有信息
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        //获取被切入方法对象
        Method method = signature.getMethod();
        //获取当前用户信息
        UserInfo currentUserInfo = UserInfoUtil.getCurrentUserInfoThreadLocal();
        ApiLogDto apiLogDto = new ApiLogDto();
        apiLogDto.setId(IdUtil.getSnowflake().nextId());
        apiLogDto.setMethod(method.getName());
        apiLogDto.setOperateUser(objectMapper.writeValueAsString(currentUserInfo));
        apiLogDto.setParams(objectMapper.writeValueAsString(pjp.getArgs()));
        apiLogDto.setResult(objectMapper.writeValueAsString(result));
        apiLogDto.setCostTime(costTime);
        if (isException) {
            apiLogDto.setException(currentException.toString());
        }
        return apiLogDto;
    }
}
