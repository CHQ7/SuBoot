package com.yunqi.starter.mail.service.impl;

import com.yunqi.starter.common.lang.Lang;
import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.mail.configuration.MailAutoConfigurationProperties;
import com.yunqi.starter.mail.entity.Email;
import com.yunqi.starter.mail.service.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * Created by @author JsckChin on 2022/1/29
 */
@Service
public class MailServiceImpl implements IMailService {

    private static final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MailAutoConfigurationProperties properties;

    @Override
    public void send(Email email) {
        MimeMessage message = javaMailSender.createMimeMessage();
        buildEmail(message, email, false);
        javaMailSender.send(message);
    }

    @Override
    public void sendHtml(Email email) {
        MimeMessage message = javaMailSender.createMimeMessage();
        buildEmail(message, email, true);
        javaMailSender.send(message);
    }

    private void buildEmail(MimeMessage mimeMessage, Email email, boolean html) {
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(properties.getUserName());
            if (Strings.isNotBlank(properties.getFrom())) {
                helper.setFrom(properties.getUserName(), properties.getFrom());
            }
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getContent(), html);
            if (Lang.isNotEmpty(email.getCc())) {
                helper.setCc(email.getCc());
            }
            // 附件
            if(Strings.isNotBlank(email.getFilePath())){
                FileSystemResource file = new FileSystemResource(new File(email.getFilePath()));
                String fileName = email.getFilePath().substring(email.getFilePath().lastIndexOf(File.separator));
                helper.addAttachment(fileName, file);
            }

        } catch (UnsupportedEncodingException e) {
            log.error("不支持字符编码", e);
            throw new RuntimeException(e);
        } catch (MessagingException e){
            throw new RuntimeException(e);
        }
    }


}
