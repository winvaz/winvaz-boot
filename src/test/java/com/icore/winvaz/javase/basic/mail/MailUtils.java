package com.icore.winvaz.javase.basic.mail;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @Deciption 发送邮件工具类
 * @Author wdq
 * @Create 2020/6/29 20:49
 * @Version 1.0.0
 */
public class MailUtils {
    /**
     * 创建session
     */
    public static Session createSession(Mail mail) {
        Properties properties = mail.getProperties();
        // 判断是否身份验证
        Authenticator authenticator = null;
        if (mail.getValidate()) {
            authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mail.getUserName(), mail.getPassword());
                }
            };
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        return Session.getDefaultInstance(properties, authenticator);
    }

    /**
     * 发送邮件
     */
    public static void send(Session session, final Mail mail) throws MessagingException, IOException {
        // 根据session创建一个邮件消息
        MimeMessage mailMessage = new MimeMessage(session);
        // 创建邮件发送者地址
        Address from = new InternetAddress(mail.getFromAddress());
        // 设置邮件消息的发送者
        mailMessage.setFrom(from);

        // 创建邮件的接收者地址，并设置到邮件消息中
        mailMessage.addRecipients(Message.RecipientType.TO, mail.getToAddress());

        // 设置抄送
        String ccAddress = mail.getCcAddress();
        if (!ccAddress.isEmpty()) {
            mailMessage.addRecipients(Message.RecipientType.CC, ccAddress);
        }

        // 设置暗送
        String bccAddress = mail.getBccAddress();
        if (!bccAddress.isEmpty()) {
            mailMessage.addRecipients(Message.RecipientType.BCC, bccAddress);
        }

        // 设置邮件消息的主题
        mailMessage.setSubject(mail.getSubject());
        // 设置邮件消息发送的时间
        mailMessage.setSentDate(new Date());

        ////////////////////////////////////
        // 创建部件集对象
        Multipart mimeMultipart = new MimeMultipart();
        // 创建一个部件
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        // 设置邮件文本内容
        mimeBodyPart.setContent(mail.getContent(), "text/html;charset=utf-8");
        // 把部件添加到部件集中
        mimeMultipart.addBodyPart(mimeBodyPart);
        ////////////////////////////////////

        // 添加附件
        List<Attach> attachList = mail.getAttachList();
        if (attachList != null) {
            for (Attach attach : attachList) {
                // 创建一个部件
                MimeBodyPart attachPart = new MimeBodyPart();
                // 设置附件文件
                attachPart.attachFile(attach.getFile());
                // 设置附件名
                attachPart.setFileName(MimeUtility.encodeText(attach.getFileName()));
                String cid = attach.getCid();
                if (cid != null) {
                    attachPart.setContentID(cid);
                }
                mimeMultipart.addBodyPart(attachPart);
            }
        }
        // 给邮件设置内容
        mailMessage.setContent(mimeMultipart);
        // 发送邮件
        Transport.send(mailMessage);
    }
}
