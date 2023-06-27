package com.wsw.summercloud.user.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/27 17:34
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserInfoControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getAllUsers"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
