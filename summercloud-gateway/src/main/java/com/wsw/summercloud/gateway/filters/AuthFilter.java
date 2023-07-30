package com.wsw.summercloud.gateway.filters;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.wsw.summercloud.api.basic.ResultStatus;
import com.wsw.summercloud.common.exception.BusinessException;
import com.wsw.summercloud.common.utils.JwtUtil;
import com.wsw.summercloud.common.utils.UserInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Map;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/7/4 16:12
 */
@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    @Autowired
    private JwtUtil jwtUtil;

    @Value("${auth.skip.urls}")
    private String[] skipAuthUrls;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        // 跳过不需要验证的路径
        if (Arrays.asList(skipAuthUrls).contains(url)) {
            return chain.filter(exchange);
        }
        // 从请求头中取出token
        String token = exchange.getRequest().getHeaders().getFirst("token");
        //token为空
        if (StrUtil.isBlank(token)) {
            throw new BusinessException(ResultStatus.TOKEN_IS_NULL);
        }
        //token无效
        boolean verified = jwtUtil.verifyToken(token);
        if (!verified) {
            throw new BusinessException(ResultStatus.TOKEN_IS_INVALID);
        }
        //解析token中的用户信息并存入ThreadLocal
        Map<String, Object> userInfo = jwtUtil.getUserInfo(token);
        Long userId = MapUtil.getLong(userInfo, "userId", 0L);
        String username = MapUtil.getStr(userInfo, "userName", "");
        UserInfoUtil.putCurrentUserInfoThreadLocal(userId, username);
        //最后清除ThreadLocal中的用户信息
        return chain.filter(exchange).doFinally(r -> UserInfoUtil.clearCurrentUserInfoThreadLocal());
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
