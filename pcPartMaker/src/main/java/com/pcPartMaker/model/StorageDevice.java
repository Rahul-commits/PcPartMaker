package com.pcPartMaker.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "StorageDevice")
public class StorageDevice {
    @Id
    private String modelNumber;
    @Column(unique = true)
    private short capacity;
    private String formFactor;


}
