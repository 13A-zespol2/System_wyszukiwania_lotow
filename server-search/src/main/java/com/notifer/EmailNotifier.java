package com.notifer;

import com.amadeus.resources.FlightOfferSearch;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailNotifier implements Notifier {

    private final String username = "pska2903@gmail.com";
    private final String password = " studia01";

    @Override
    public void sendNotification(String contact, FlightOfferSearch flightOrder) throws MessagingException {
        String username = "apitestpipprojekt2021@gmail.com";
        String password = " Qweasdzxc!1";
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
                InternetAddress.parse("wojtekgrelewicz@gmail.com", false));

        msg.setSubject("System_Rezerwacji_Lotow");
        msg.getMessageNumber();
        msg.setText("Zarezerowano bilet");

        msg.saveChanges();
        msg.setSentDate(new Date());
        Transport.send(msg);

    }
}
