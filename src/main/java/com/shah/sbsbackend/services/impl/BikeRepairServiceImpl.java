package com.shah.sbsbackend.services.impl;

import com.shah.sbsbackend.exceptions.MessageNotSentException;
import com.shah.sbsbackend.models.BikeRepair;
import com.shah.sbsbackend.repos.BikeRepairRepo;
import com.shah.sbsbackend.services.BikeRepairService;
import com.shah.sbsbackend.services.SendWhatsappMessageService;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class BikeRepairServiceImpl implements BikeRepairService {

    private final BikeRepairRepo serviceRepo;
    private final SendWhatsappMessageService sendWhatsappMessageService;

    public BikeRepairServiceImpl(BikeRepairRepo serviceRepo, SendWhatsappMessageService sendWhatsappMessageService) {
        this.serviceRepo = serviceRepo;
        this.sendWhatsappMessageService = sendWhatsappMessageService;
    }

    @Override
    public String processBooking(BikeRepair service) {
        try {
            bookService(service);

            String s = sendWhatsappMessageService.sendWhatsappMessage(service.getContactNo(), "Your service has been booked successfully for " + service.getBikeModel() + ", and is scheduled for " + service.getPreferredTime()+ ". Please reach out to us for any queries. Thank you!");
            System.out.println(s);
            Message fetchedMessage = Message.fetcher(s).fetch();
            System.out.println("Message status: " + fetchedMessage.getStatus());
            System.out.println("Error Message: " + fetchedMessage.getErrorMessage());
        } catch (MessageNotSentException e) {

//            throw new MessageNotSentException("Error sending message: " + e.getMessage());
        }
        return "Service for " + service.getBikeModel() + " booked successfully.";
    }


    @Override
    public BikeRepair bookService(BikeRepair service) {
        return this.serviceRepo.save(service);
    }
}
