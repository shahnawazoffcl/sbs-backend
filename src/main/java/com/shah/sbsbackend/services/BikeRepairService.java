package com.shah.sbsbackend.services;

import com.shah.sbsbackend.models.BikeRepair;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BikeRepairService {

    String processBooking(BikeRepair service);

}
