package com.gpcoder.javaxmail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.gpcoder.MailConfig;
import java.sql.SQLException;
import javax.mail.internet.AddressException;
import longnpt.daos.UserDAO;

public class SendMailTLS {

    public static void sendVerify(String id, String email) throws AddressException, MessagingException, SQLException {
        String code = UserDAO.getCode(id);
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", MailConfig.HOST_NAME);
        props.put("mail.smtp.user", MailConfig.APP_EMAIL);
        props.put("mail.smtp.password", MailConfig.APP_PASSWORD);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(false);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(MailConfig.APP_EMAIL));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        //smessage.setFrom(new InternetAddress(show_name + "<" + from_userName + ">"));
        message.setSubject("asd");
        message.setText("Your code: " + code);

        try (Transport transport = session.getTransport("smtp")) {
            transport.connect(MailConfig.HOST_NAME, MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
        }

        System.out.println("send an email to success");
    }
}
