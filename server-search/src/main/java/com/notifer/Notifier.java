package com.notifer;

import javax.mail.MessagingException;

public interface Notifier {
    void sendNotification(String contact, String message) throws MessagingException;

}
