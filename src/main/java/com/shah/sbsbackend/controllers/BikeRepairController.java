package com.shah.sbsbackend.controllers;


import com.shah.sbsbackend.models.BikeRepair;
import com.shah.sbsbackend.services.BikeRepairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/service")
@Slf4j
public class BikeRepairController {

    private final BikeRepairService bikeRepairServiceService;


    public BikeRepairController(BikeRepairService bikeRepairService) {
        this.bikeRepairServiceService = bikeRepairService;
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookService(@RequestBody BikeRepair bikeRepair) {

            try {
                log.info("Booking BikeRepair");
                if (bikeRepair.getBikeModel() == null) {
                    log.error("BikeModel is null");
                    throw new IllegalArgumentException("Service cannot be null");
                }
                String serv = bikeRepairServiceService.processBooking(bikeRepair);
                log.info(serv);
                return ResponseEntity.ok(serv);
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid request");
            }

    }


}
