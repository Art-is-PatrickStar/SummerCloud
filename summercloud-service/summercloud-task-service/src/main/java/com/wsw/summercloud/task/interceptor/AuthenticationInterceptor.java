package com.wsw.summercloud.task.interceptor;

import cn.hutool.core.util.StrUtil;
import com.wsw.summercloud.api.basic.ResultStatus;
import com.wsw.summercloud.common.exception.BusinessException;
import com.wsw.summercloud.common.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/28 15:18
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            throw new BusinessException(ResultStatus.TOKEN_IS_NULL);
        }
        boolean verified = jwtUtil.verifyToken(token);
        if (!verified) {
            throw new BusinessException(ResultStatus.TOKEN_IS_INVALID);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
