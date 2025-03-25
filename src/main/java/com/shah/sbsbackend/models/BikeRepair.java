package com.shah.sbsbackend.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
        // Do not set 'column2' value explicitly
        if (serviceStatus == null) {
            serviceStatus = "PENDING";  // Only set manually if not set
        }
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBikeModel() {
        return bikeModel;
    }

    public void setBikeModel(String bikeModel) {
        this.bikeModel = bikeModel;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPreferredTime() {
        return preferredTime;
    }

    public void setPreferredTime(String preferredTime) {
        this.preferredTime = preferredTime;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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
