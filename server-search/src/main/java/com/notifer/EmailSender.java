package com.notifer;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


/**
 * Klasa ta służy do wysłania użytkownikowi powiadomienia E-Mail z danymi dotyczącymi zarezerwowanego lotu.
 */
public class EmailSender {
    private final String username = "apitestpipprojekt2021@gmail.com";
    private final String password = " Qweasdzxc!1";

    public EmailSender() {
    }

    void sendEmail(String subject, String text, String addressEmail) throws MessagingException {

        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("apitestpipprojekt2021", password);
            }
        });


        MimeMessage msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(username));
        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(addressEmail, false));

        msg.setSubject(subject);
        msg.getMessageNumber();
        msg.setText(text);

        msg.saveChanges();
        msg.setSentDate(new Date());
        Transport.send(msg);

    }


}
