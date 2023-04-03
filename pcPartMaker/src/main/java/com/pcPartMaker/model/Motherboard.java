package com.pcPartMaker.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "motherboard")
public class Motherboard {
    @Id
    private String modelNumber;
    @Column(unique = true)
    private String productName;
    private boolean eccCompatibility;
    private int wattage;


}
