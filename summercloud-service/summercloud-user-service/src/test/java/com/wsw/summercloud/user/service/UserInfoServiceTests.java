package com.wsw.summercloud.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/25 0:03
 */
@Slf4j
@SpringBootTest
public class UserInfoServiceTests {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserInfoService userInfoService;

    @Test
    void getAllUsersTest() throws Exception {
        log.info(objectMapper.writeValueAsString(userInfoService.getAllUsers()));
    }
}
