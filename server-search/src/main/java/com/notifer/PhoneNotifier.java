package com.notifer;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;


public class PhoneNotifier implements Notifier {
    @Override
    public void sendNotification(String contact, String message) {
        NexmoClient client = new NexmoClient.Builder()
                .apiKey("b9f4b030")
                .apiSecret("4cTDs71dcmgpyE1T")
                .build();

        String messageText = "ZAREZERWOWANO BILET";
        TextMessage messagePhone = new TextMessage("Vonage APIs", "48696886115", message);

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(messagePhone);

        for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
            System.out.println(responseMessage);
        }
    }
}
