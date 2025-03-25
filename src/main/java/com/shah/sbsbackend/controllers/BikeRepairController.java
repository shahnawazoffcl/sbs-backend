package com.shah.sbsbackend.controllers;


import com.shah.sbsbackend.models.BikeRepair;
import com.shah.sbsbackend.services.BikeRepairService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/service")
public class BikeRepairController {

    private final BikeRepairService bikeRepairServiceService;


    public BikeRepairController(BikeRepairService bikeRepairService) {
        this.bikeRepairServiceService = bikeRepairService;
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookService(@RequestBody BikeRepair bikeRepair) {

            try {

                if (bikeRepair.getBikeModel() == null) {
                    throw new IllegalArgumentException("Service cannot be null");
                }
                String serv = bikeRepairServiceService.processBooking(bikeRepair);
                return ResponseEntity.ok(serv);
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid request");
            }

    }


}
