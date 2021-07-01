package xyz.chenjinsui.travel.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {
    private static final String USER = "fengling_bot@163.com"; //发件人称号 同邮件地址
    private static final String PASSWORD = "OWLONLVNYCLGJGZN"; //客户端授权码


    /**
     * 发送邮件
     * @param to 收件人邮箱
     * @param text 邮件正文
     * @param title 标题
     * @return 是否发送成功
     */
    public static boolean sendMail(String to, String text, String title){
        try{
            final Properties props = new Properties();
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.host","smtp.163.com");

            //发件人账号和密码
            props.put("mail.user",USER);
            props.put("mail.password", PASSWORD);

            //构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            //使用环境属性和授权信息,创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            //创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            //设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress from = new InternetAddress(username);
            message.setFrom(from);

            //设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            //设置邮件标题
            message.setSubject(title);

            //设置邮件的内容体
            message.setContent(text, "text/html;charset=UTF-8");

            //发送邮件
            Transport.send(message);
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        MailUtils.sendMail("1275347493@qq.com", "HelloWorld", "测试邮件");
        System.out.println("发送成功");
    }
}
