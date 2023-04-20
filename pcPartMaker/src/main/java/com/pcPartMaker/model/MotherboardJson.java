package com.pcPartMaker.model;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class MotherboardJson implements Serializable {
    public MotherboardJson(int modelNumber, String productName, boolean eccCompatibility, int wattage, int dimmSlotTypeId, int memoryTypeId, float rating, double price, int id, short quantity, short pciGeneration) {
        this.modelNumber = modelNumber;
        this.productName = productName;
        this.eccCompatibility = eccCompatibility;
        this.wattage = wattage;
        this.dimmSlotTypeId = dimmSlotTypeId;
        this.memoryTypeId = memoryTypeId;
        this.rating = rating;
        this.price = price;
        this.id = id;
        this.quantity = quantity;
        this.pciGeneration = pciGeneration;
    }

    private int modelNumber;
    private String productName;
    private boolean eccCompatibility;
    private int wattage;

    private int dimmSlotTypeId;

    private  int memoryTypeId;

    private float rating;
    private double price;

    private int id;

    private short quantity;

    private short pciGeneration;


}
