package com.shah.sbsbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
public class BikeRepair extends BaseModel implements Serializable {
    private String name;
    private String bikeModel;
    private String serviceType;
    private String contactNo;
    private String address;
    private String preferredTime;
    private String serviceStatus;
    private Date createdDate;
    private String createdBy;

    @PrePersist
    public void prePersist() {
        if (serviceStatus == null) {
            serviceStatus = "PENDING";
        }
    }

    @Override
    public String toString() {
        return "BikeService{" +
                "name='" + name + '\'' +
                ", bikeModel='" + bikeModel + '\'' +
                ", serviceType=" + serviceType +
                ", contactNo='" + contactNo + '\'' +
                ", address='" + address + '\'' +
                ", preferredTime='" + preferredTime + '\'' +
                '}';
    }
}
