package com.notifer;

import javax.mail.MessagingException;

public class EmailNotifier implements Notifier {
    private final EmailSender emailSender;

    public EmailNotifier() {
        this.emailSender = new EmailSender();
    }

    @Override
    public void sendNotification(String contact, String message) throws MessagingException {
        this.emailSender.sendEmail("Rezerwacja biletow 13A", message, contact);
    }
}
