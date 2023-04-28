package com.wsw.summercloud.user.utils;

import com.wsw.summercloud.common.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/28 13:57
 */
@Slf4j
@SpringBootTest
public class JwtUtilTests {
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void createToken() {
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("userId", 1);
        userInfoMap.put("userName", "wsw");
        userInfoMap.put("email", "");
        log.info(jwtUtil.createToken(userInfoMap));
    }

    @Test
    void verifyToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ3c3ciLCJwYXNzd29yZCI6IjEyMzQ1NiIsImVtYWlsIjoiIiwiaXNEZWxldGUiOjAsImNyZWF0ZWRUaW1lIjoiMjAyMy0wNC0yOFQwNjoyMzozMy45NDcrMDA6MDAiLCJ1cGRhdGVkVGltZSI6IjIwMjMtMDQtMjhUMDY6MjM6MzMuOTQ3KzAwOjAwIiwiaWF0IjoxNjgyNjYzODc4LCJuYmYiOjE2ODI2NjM4NzgsImV4cCI6MTY4MjcwNzA3OH0.CCHSZVSCXJlYq80SUkBzaa5kh_XwF_z1Wen4xq5jCbg";
        log.info(String.valueOf(jwtUtil.verifyToken(token)));
    }
}
