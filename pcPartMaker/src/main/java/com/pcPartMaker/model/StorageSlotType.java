package com.pcPartMaker.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "StorageSlotType")
public class StorageSlotType {
    @Id
    private String interfacetype;
    @Id
    private String Version;



}
