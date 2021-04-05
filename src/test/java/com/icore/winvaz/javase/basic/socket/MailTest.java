package com.icore.winvaz.javase.basic.socket;

import lombok.Data;

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
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @Author 核心技术 卷II 第四章网络 230 邮件测试
 * @Create 2021/4/5 20:28
 * @Version 1.0.0
 */
public class MailTest {
    public static void main(String[] args) throws IOException, MessagingException {
        Properties props = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get("mail", "mail.properties"))) {
            props.load(inputStream);
        }
        List<String> lines = Files.readAllLines(Paths.get(args[0]), Charset.forName("UTF-8"));
        String from = lines.get(0);
        String to = lines.get(1);
        String subject = lines.get(2);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            sb.append(lines.get(i));
            sb.append("\n");
        }

        Console console = System.console();
        String password = new String(console.readPassword("Password: "));

        Session session = Session.getDefaultInstance(props);
        // session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setText(sb.toString());
        Transport transport = session.getTransport();
        try {
            transport.connect(null, password);
            transport.sendMessage(message, message.getAllRecipients());
        } finally {
            transport.close();
        }
    }
}

/**
 * 表示邮件类，你需要设置：账户名和密码，收件人，抄送(可选)，暗送(可选)，主题，内容，以及附件(可选)
 *
 * @Author wdq
 * @Create 2020/6/29 20:32
 * @Version 1.0.0
 */
@Data
class Mail {
    /**
     * 发送邮件的服务器的IP(或主机地址)
     */
    private String mailServerHost;
    /**
     * 发送邮件的服务器的端口
     */
    private String mailServerPort = "25";
    /**
     * 发件人邮箱地址
     */
    private String fromAddress;
    /**
     * 收件人邮箱地址
     */
    private StringBuilder toAddress = new StringBuilder();
    /**
     * 抄送人邮箱地址
     */
    private StringBuilder ccAddress = new StringBuilder();
    /**
     * 暗送人邮箱地址
     */
    private StringBuilder bccAddress = new StringBuilder();
    /**
     * 登陆邮件发送服务器的用户名
     */
    private String userName;
    /**
     * 登陆邮件发送服务器的密码
     */
    private String password;
    /**
     * 是否需要身份验证
     */
    private Boolean validate = false;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件的文本内容
     */
    private String content;

    /**
     * 邮件附件的文件名
     */
    private List<Attach> attachList;

    public Mail(String fromAddress, StringBuilder toAddress, String subject, String content) {
        this.fromAddress = fromAddress;
        this.toAddress.append(toAddress);
        this.subject = subject;
        this.content = content;
    }

    /**
     * 获取身份验证信息
     */
    public Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", this.mailServerHost);
        properties.put("mail.smtp.port", this.mailServerPort);
        properties.put("mail.smtp.auth", validate ? "true" : "false");
        return properties;
    }

    /**
     * 添加收件人，可以是多个收件人
     */
    public void addToAddress(String to) {
        if (this.toAddress.length() > 0) {
            this.toAddress.append(",");
        }
        this.toAddress.append(to);
    }

    /**
     * 添加抄送人，可以是多个抄送人
     */
    public void addCcAddress(String cc) {
        if (this.ccAddress.length() > 0) {
            this.ccAddress.append(",");
        }
        this.ccAddress.append(cc);
    }

    /**
     * 添加暗送人，可以是多个暗送人
     */
    public void addBccAddress(String bcc) {
        if (this.bccAddress.length() > 0) {
            this.bccAddress.append(",");
        }
        this.bccAddress.append(bcc);
    }

    public List<Attach> getAttachList() {
        return attachList;
    }

    /**
     * 添加附件，可以添加多个附件
     */
    public void setAttachList(List<Attach> attachList) {
        this.attachList = attachList;
    }
}

/**
 * 附件类
 *
 * @Author wdq
 * @Create 2020/6/29 20:29
 * @Version 1.0.0
 */
@Data
class Attach {

    private String cid;
    /**
     * 文件
     */
    private File file;
    /**
     * 文件名称
     */
    private String fileName;
}

/**
 * @Deciption 发送邮件工具类
 * @Author wdq
 * @Create 2020/6/29 20:49
 * @Version 1.0.0
 */
class MailUtils {
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
        mailMessage.addRecipients(Message.RecipientType.TO, mail.getToAddress().toString());

        // 设置抄送
        String ccAddress = mail.getCcAddress().toString();
        if (!ccAddress.isEmpty()) {
            mailMessage.addRecipients(Message.RecipientType.CC, ccAddress);
        }

        // 设置暗送
        String bccAddress = mail.getBccAddress().toString();
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