package com.pcPartMaker.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class RamJson implements Serializable {

    private Integer id;
    private String modelName;

    private int moduleAmount;

    private int numberOfDims;
    private int modelNumber;

    private String manufacturerName;

    private int frequency;
    private boolean ecc;

    private float wattage;
    private float rating;
    private double price;
    private String ramGeneration;
    String slotName;

    public RamJson(Integer id, String modelName, int moduleAmount, int numberOfDims, int modelNumber, String manufacturerName, int frequency, boolean ecc, float wattage, float rating, double price, String ramGeneration, String slotName) {
        this.id = id;
        this.modelName = modelName;
        this.moduleAmount = moduleAmount;
        this.numberOfDims = numberOfDims;
        this.modelNumber = modelNumber;
        this.manufacturerName = manufacturerName;
        this.frequency = frequency;
        this.ecc = ecc;
        this.wattage = wattage;
        this.rating = rating;
        this.price = price;
        this.ramGeneration = ramGeneration;
        this.slotName = slotName;
    }
}



