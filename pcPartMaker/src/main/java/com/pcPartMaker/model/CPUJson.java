package com.pcPartMaker.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class CPUJson implements Serializable {

    private Integer id;
    private int modelNumber;
    private String modelName;
    private short numberOfCores;
    private String architecture;
    private Boolean eccCompatibility;
    private int frequency;
    private int TDP;
    private int wattage;
    private float rating;
    private double price;
    private int clock;
    private String manufacturerName;
    private String socket;

    private String ramGeneration;

    public CPUJson(Integer id, int modelNumber, String modelName, short numberOfCores, String architecture, Boolean eccCompatibility, int frequency, int TDP, int wattage, float rating, double price, int clock, String manufacturerName, String socket, String ramGeneration) {
        this.id = id;
        this.modelNumber = modelNumber;
        this.modelName = modelName;
        this.numberOfCores = numberOfCores;
        this.architecture = architecture;
        this.eccCompatibility = eccCompatibility;
        this.frequency = frequency;
        this.TDP = TDP;
        this.wattage = wattage;
        this.rating = rating;
        this.price = price;
        this.clock = clock;
        this.manufacturerName = manufacturerName;
        this.socket = socket;
        this.ramGeneration = ramGeneration;
    }
}


