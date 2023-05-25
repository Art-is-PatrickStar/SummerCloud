package com.wsw.summercloud.task.service;

import java.io.File;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/5/25 11:13
 */
public interface MailClientService {
    void sendMail(String subject, String content, String[] to, String[] cc, File file);
}
