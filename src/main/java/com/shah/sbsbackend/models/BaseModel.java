package com.shah.sbsbackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid2")
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
