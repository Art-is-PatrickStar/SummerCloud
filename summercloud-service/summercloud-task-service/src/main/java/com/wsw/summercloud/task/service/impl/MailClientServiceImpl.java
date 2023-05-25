package com.wsw.summercloud.task.service.impl;

import com.wsw.summercloud.api.basic.ResultStatus;
import com.wsw.summercloud.common.exception.BusinessException;
import com.wsw.summercloud.task.service.MailClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/5/25 11:21
 */
@Slf4j
@Service
public class MailClientServiceImpl implements MailClientService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.open:true}")
    private boolean mailOpen;

    @Value("${mail.from}")
    private String mailFrom;

    @Override
    public void sendMail(String subject, String content, String[] to, String[] cc, File file) {
        if (!mailOpen) {
            log.info("跳过发送!");
            return;
        }
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            messageHelper.setFrom(mailFrom);
            messageHelper.setTo(to);
            messageHelper.setCc(cc);
            if (Objects.nonNull(file)) {
                messageHelper.addAttachment(file.getName(), file);
            }
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new BusinessException(ResultStatus.SEND_MAIL_EXCEPTION, ResultStatus.SEND_MAIL_EXCEPTION.getMsg() + e.getMessage());
        }
    }
}
