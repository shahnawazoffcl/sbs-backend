package com.shah.sbsbackend.services.impl;

import com.shah.sbsbackend.exceptions.MessageNotSentException;
import com.shah.sbsbackend.models.BikeRepair;
import com.shah.sbsbackend.repos.BikeRepairRepo;
import com.shah.sbsbackend.services.BikeRepairService;
import com.shah.sbsbackend.services.SendWhatsappMessageService;
import com.twilio.rest.api.v2010.account.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class BikeRepairServiceImpl implements BikeRepairService {

    private final BikeRepairRepo serviceRepo;
    private final SendWhatsappMessageService sendWhatsappMessageService;

    public BikeRepairServiceImpl(BikeRepairRepo serviceRepo, SendWhatsappMessageService sendWhatsappMessageService) {
        this.serviceRepo = serviceRepo;
        this.sendWhatsappMessageService = sendWhatsappMessageService;
    }

    @Override
    public String processBooking(BikeRepair service) {
        log.info("Booking BikeRepair Service starts");
        try {
            bookService(service);

            String s = sendWhatsappMessageService.sendWhatsappMessage(service.getContactNo(), "Hi Asnad, a new service has been received for vehicle " + service.getBikeModel() + ", to be serviced on " + service.getPreferredTime()+ ".");
            log.info("Message Id: {}",s);
            Message fetchedMessage = Message.fetcher(s).fetch();
            log.info("Message status: {}", fetchedMessage.getStatus());
            log.info("Error Message: {}", fetchedMessage.getErrorMessage());
        } catch (MessageNotSentException e) {
            log.error("Message not sent", e);
            throw new MessageNotSentException("Error sending message: " + e.getMessage());
        }
        return "Service for " + service.getBikeModel() + " booked successfully.";
    }



    public BikeRepair bookService(BikeRepair service) {
        return this.serviceRepo.save(service);
    }
}
