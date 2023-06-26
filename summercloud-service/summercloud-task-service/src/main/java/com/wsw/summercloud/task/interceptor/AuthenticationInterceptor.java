package com.wsw.summercloud.task.interceptor;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.wsw.summercloud.api.basic.ResultStatus;
import com.wsw.summercloud.common.exception.BusinessException;
import com.wsw.summercloud.common.utils.JwtUtil;
import com.wsw.summercloud.common.utils.UserInfoUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/28 15:18
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
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
        //解析token中的用户信息并存入ThreadLocal
        Map<String, Object> userInfo = jwtUtil.getUserInfo(token);
        Long userId = MapUtil.getLong(userInfo, "userId", 0L);
        String username = MapUtil.getStr(userInfo, "userName", "");
        UserInfoUtil.putCurrentUserInfoThreadLocal(userId, username);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除ThreadLocal中的用户信息
        UserInfoUtil.clearCurrentUserInfoThreadLocal();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
