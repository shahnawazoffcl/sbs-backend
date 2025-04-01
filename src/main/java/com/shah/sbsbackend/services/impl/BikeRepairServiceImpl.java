package com.shah.sbsbackend.services.impl;

import com.shah.sbsbackend.exceptions.MessageNotSentException;
import com.shah.sbsbackend.models.BikeRepair;
import com.shah.sbsbackend.repos.BikeRepairRepo;
import com.shah.sbsbackend.services.BikeRepairService;
import com.shah.sbsbackend.services.SendWhatsappMessageService;
import com.twilio.rest.api.v2010.account.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class BikeRepairServiceImpl implements BikeRepairService {

    @Value("${send.whatsapp.message}")
    private String sendWhatsappMessage;

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
            String formattedDate = getString(service);
            service.setPreferredTime(formattedDate);
            if (sendWhatsappMessage.equals("yes")) {
                log.info("Booking BikeRepair Service sendWhatsappMessage starting");
                String s = sendWhatsappMessageService.sendWhatsappMessage(service.getContactNo(), "Hi Asnad, a new service has been received for vehicle " + service.getBikeModel() + ", to be serviced on " + service.getPreferredTime() + ".");
                log.info("Message Id: {}", s);
                Message fetchedMessage = Message.fetcher(s).fetch();
                if (fetchedMessage.getStatus().equals(Message.Status.FAILED) || fetchedMessage.getStatus().equals(Message.Status.UNDELIVERED)) {
                    throw new MessageNotSentException("Error sending message: " + fetchedMessage.getErrorMessage());
                }
            }
        } catch (MessageNotSentException e) {
            log.error("Message not sent", e);
            throw new MessageNotSentException("Error sending message: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return "Service for " + service.getBikeModel() + " booked successfully.";
    }

    private static String getString(BikeRepair service) throws ParseException {
        String dateStr = service.getPreferredTime();
        dateStr = dateStr.replaceAll("\\(.*\\)", "").trim();
        dateStr = dateStr.replace("GMT", "").trim();
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss zzz");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = inputFormat.parse(dateStr);
        return outputFormat.format(date);
    }


    public BikeRepair bookService(BikeRepair service) {
        return this.serviceRepo.save(service);
    }
}
