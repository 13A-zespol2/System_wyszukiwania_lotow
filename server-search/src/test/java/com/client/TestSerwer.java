package com.client;


import com.repository.model.database.MyTraveler;
import com.repository.model.database.TravelerDocument;
import com.repository.model.database.TravelerPhone;
import com.strategy.CustomTravelerCreationStrategy;
import com.strategy.UserBasedTravelerCreationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class TestSerwer {


    @Test
    public void strategyCustomTest() {
        CustomTravelerCreationStrategy customTravelerCreationStrategy = CustomTravelerCreationStrategy.builder()
                .idMyTraveler(1)
                .countryCallingCode(48)
                .phoneNumber(123345567)
                .deviceType("MOBILE")
                .documentType("PASSPORT")
                .numberDocument("CYA123123")
                .expireDate("2030-11-11")
                .issuanceCountry("PL")
                .nationality("PL")
                .name("Karol")
                .surname("Grelewicz")
                .dateOfBirth("1998-11-24")
                .build();


        assertTrue(customTravelerCreationStrategy.createTraveler() != null);

    }

    @Test
    public void strategyUserBasedTest() {
        MyTraveler myTraveler = new MyTraveler.Builder()
                .name("Karol")
                .surname("Mik")
                .dateOfBirth("1998-24-11")
                .build();

        TravelerDocument travelerDocument = new TravelerDocument.Builder()
                .documentType("PASSPORT")
                .expireDate("2030-11-11")
                .numberDocument("CYA123123")
                .nationality("PL")
                .issuanceCountry("PL")
                .build();

        TravelerPhone travelerPhone = new TravelerPhone.Builder()
                .deviceType("MOBILE")
                .phoneNumber(123123123)
                .countryCallingCode(48)
                .build();

        UserBasedTravelerCreationStrategy userBasedTravelerCreationStrategy = new UserBasedTravelerCreationStrategy(myTraveler, travelerDocument, travelerPhone);

        assertTrue(userBasedTravelerCreationStrategy.createTraveler() != null);

    }


   /* @SneakyThrows
    @Test
    void sendEmail() {


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

        try {
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("wojtekgrelewicz@gmail.com", false));

            msg.setSubject("System_Rezerwacji_Lotow");
            msg.getMessageNumber();
            msg.setText("Zarezerowano bilet");

            msg.saveChanges();
            msg.setSentDate(new Date());
            Transport.send(msg);
            log.info("dsa");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @Test
    void sendSMS() {
        NexmoClient client = new NexmoClient.Builder()
                .apiKey("b9f4b030")
                .apiSecret("4cTDs71dcmgpyE1T")
                .build();

        String messageText = "ZAREZERWOWANO BILET";
        TextMessage message = new TextMessage("Vonage APIs", "48696886115", messageText);

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
            System.out.println(responseMessage);
        }
    }*/


}
