package com.wsw.summercloud.task.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/5/25 14:47
 */
@SpringBootTest
public class MailClientServiceTests {
    @Autowired
    private MailClientService mailClientService;

    @Value("${mail.to}")
    private String[] mailTo;

    @Value("${mail.cc}")
    private String[] mailCc;

    @Test
    void sendMail() {
        String subject = "测试邮件";
        String content = "测试邮件内容";
        File file = new File("/Users/summer15/Desktop/test");
        mailClientService.sendMail(subject, content, mailTo, mailCc, file);
    }
}
