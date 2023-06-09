package com.wsw.summercloud.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

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
    //    @Autowired
    //    private UserInfoService userInfoService;
    @MockBean
    private UserInfoService userInfoService;

    @Test
    void getAllUsersTest() throws Exception {
        //log.info(objectMapper.writeValueAsString(userInfoService.getAllUsers()));
        BDDMockito.given(userInfoService.getAllUsers()).willReturn(new ArrayList<>());
        assert userInfoService.getAllUsers().size() == 0;
    }
}
