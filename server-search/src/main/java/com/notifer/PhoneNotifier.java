package com.notifer;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;

/**
 * Klasa wykorzystywana we wzorcu ,,Decorator".
 * Służy ona do wysłania użytkownikowi powiadomienia SMS z danymi dotyczącymi zarezerwowanego lotu.
 */
public class PhoneNotifier implements Notifier {
    @Override
    public void sendNotification(String contact, String message) {
        NexmoClient client = new NexmoClient.Builder()
                .apiKey("e1e3cb72")
                .apiSecret("QEkj04elbaBuc3YR")
                .build();

        TextMessage messagePhone = new TextMessage("Vonage APIs", "48721625701", message);

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(messagePhone);

        for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
            System.out.println(responseMessage);
        }
    }
}
