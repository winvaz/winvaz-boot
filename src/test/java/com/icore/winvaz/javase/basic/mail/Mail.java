package com.icore.winvaz.javase.basic.mail;

import java.util.List;
import java.util.Properties;

/**
 * 表示邮件类，你需要设置：账户名和密码，收件人，抄送(可选)，暗送(可选)，主题，内容，以及附件(可选)
 *
 * @Author wdq
 * @Create 2020/6/29 20:32
 * @Version 1.0.0
 */
public class Mail {
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
    private boolean validate = false;
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

    public Mail() {

    }

    public Mail(String fromAddress, StringBuilder toAddress) {
        this(fromAddress, toAddress, null, null);
    }

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

    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public String getMailServerPort() {
        return mailServerPort;
    }

    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress.toString();
    }

    public String getCcAddress() {
        return ccAddress.toString();
    }

    public String getBccAddress() {
        return bccAddress.toString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
