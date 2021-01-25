package com.notifer;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;


public class PhoneNotifier implements Notifier {
    @Override
    public void sendNotification(String contact, String message) {
        NexmoClient client = new NexmoClient.Builder()
                .apiKey("e1e3cb72")
                .apiSecret("QEkj04elbaBuc3YR")
                .build();

        String messageText = "ZAREZERWOWANO BILET";
        TextMessage messagePhone = new TextMessage("Vonage APIs", "48721625701", message);

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(messagePhone);

        for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
            System.out.println(responseMessage);
        }
    }
}
