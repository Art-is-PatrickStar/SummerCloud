package com.wsw.summercloud.common.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/28 13:15
 */
@Component
public class JwtUtil {
    //token密钥
    @Value("${jwt.secret.key:summercloud}")
    private String secretKey;
    //token有效期(秒)
    @Value("${jwt.token.expire.time:43200}")
    private int tokenExpireTime;

    /**
     * 创建token
     *
     * @param userInfoMap
     * @return String
     */
    public String createToken(Map<String, Object> userInfoMap) {
        DateTime now = DateUtil.date();
        DateTime expireTime = now.offsetNew(DateField.SECOND, tokenExpireTime);
        //签发时间
        userInfoMap.put(JWTPayload.ISSUED_AT, now);
        //生效时间
        userInfoMap.put(JWTPayload.NOT_BEFORE, now);
        //过期时间
        userInfoMap.put(JWTPayload.EXPIRES_AT, expireTime);
        return JWTUtil.createToken(userInfoMap, secretKey.getBytes());
    }

    /**
     * 验证token
     *
     * @param token
     * @return boolean
     */
    public boolean verifyToken(String token) {
        JWT jwt = JWTUtil.parseToken(token).setKey(secretKey.getBytes());
        return jwt.validate(0);
    }
}
