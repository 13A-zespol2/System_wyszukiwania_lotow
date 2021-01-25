package com.notifer;

import javax.mail.MessagingException;

/**
 * Interfejs wykorzystywany do wzorca projektowego ,,Decorator".
 */
public interface Notifier {
    void sendNotification(String contact, String message) throws MessagingException;

}
