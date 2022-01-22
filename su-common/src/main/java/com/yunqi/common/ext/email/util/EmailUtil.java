package com.yunqi.common.ext.email.util;

import cn.hutool.core.util.ArrayUtil;
import com.yunqi.common.ext.email.config.EmailConfig;
import lombok.extern.slf4j.Slf4j;
import org.nutz.lang.Strings;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * <p>
 *     邮件发送工具类
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
@Slf4j
public class EmailUtil {

    private static JavaMailSenderImpl mailSender = buildMailSender();


    /**
     * 构建邮件实例
     * @return 构建邮件实例
     */
    private static JavaMailSenderImpl buildMailSender() {
        JavaMailSenderImpl email = new JavaMailSenderImpl();
        email.setHost(EmailConfig.HOST_NAME);
        email.setPort(Integer.parseInt(EmailConfig.SMTP_PORT));
        email.setUsername(EmailConfig.USER_NAME);
        email.setPassword(EmailConfig.USER_PASSWORD);
        email.setDefaultEncoding("Utf-8");
        if(EmailConfig.SSL){
            Properties prop = new Properties();
            prop.put("mail.smtp.ssl.enable", EmailConfig.SSL);
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            email.setJavaMailProperties(prop);
        }
        return email;
    }

    private static MimeMessageHelper buildEmail(MimeMessage mimeMessage, String to, String subject, String content, String... cc) {
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(EmailConfig.USER_NAME);
            if (Strings.isNotBlank(EmailConfig.FROM_NAME)) {
                helper.setFrom(EmailConfig.USER_NAME, EmailConfig.FROM_NAME);
            }
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            if (ArrayUtil.isNotEmpty(cc)) {
                helper.setCc(cc);
            }
            return helper;
        } catch (UnsupportedEncodingException e) {
            log.error("不支持字符编码", e);
            throw new RuntimeException(e);
        } catch (MessagingException e){
            throw new RuntimeException(e);
        }
    }


    /**
     * 发送文本邮件,默认支持HTML格式
     *
     * @param to      收件人地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param cc      抄送地址
     */
    public static void send(String to, String subject, String content, String... cc) {
        MimeMessage message = mailSender.createMimeMessage();
        buildEmail(message, to, subject, content, cc);
        mailSender.send(message);
    }


    /**
     * 发送文本邮件,默认支持HTML格式
     *
     * @param to       收件人地址
     * @param subject  邮件主题
     * @param content  邮件内容
     * @param filePath 附件地址
     * @param cc       抄送地址
     * @throws MessagingException 邮件发送异常
     */
    public static void sendAttachments(String to, String subject, String content, String filePath, String... cc) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = buildEmail(message, to, subject, content, cc);

        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
        helper.addAttachment(fileName, file);
        mailSender.send(message);
    }


    /**
     * 发送文本邮件,支持发送正文中有静态资源的邮件
     * 默认支持HTML格式
     *
     * @param to      收件人地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param rscPath 静态资源地址
     * @param rscId   静态资源id
     * @param cc      抄送地址
     * @throws MessagingException 邮件发送异常
     */
    public static void sendResourceMail(String to, String subject, String content, String rscPath, String rscId, String... cc) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = buildEmail(message, to, subject, content, cc);

        FileSystemResource res = new FileSystemResource(new File(rscPath));
        helper.addInline(rscId, res);
        mailSender.send(message);
    }
}
