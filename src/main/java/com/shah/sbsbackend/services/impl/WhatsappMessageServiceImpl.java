package com.shah.sbsbackend.services.impl;

import com.shah.sbsbackend.exceptions.MessageNotSentException;
import com.shah.sbsbackend.services.SendWhatsappMessageService;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;


@Service
public class WhatsappMessageServiceImpl implements SendWhatsappMessageService {

    @Value("${twilio.authToken}")
    private String AUTH_TOKEN;

    @Value("${twilio.accountSid}")
    private String ACCOUNT_SID;

    @Value("${twilio.from}")
    private String WHATSAPP_NUMBER;

    @Value("${twilio.to}")
    private String TO_NUMBER;

    @Override
    public String sendWhatsappMessage(String to, String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        try {
            Message message = Message.creator(
                            new PhoneNumber(TO_NUMBER),
                            new PhoneNumber(WHATSAPP_NUMBER),
                            messageBody)
                    .create();
            return message.getSid();
        }
        catch (Exception e) {
            throw new MessageNotSentException( "Error sending message: " + e.getMessage());
        }
    }
}
