package com.pcPartMaker.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class GraphicCardJson implements Serializable {
    public GraphicCardJson(Integer id, String modelNumber, String modelName, int process, int TDP, int wattage, float tfs, float clockSpeed, float rating, double price, String manufacturerName, short generation) {
        this.id = id;
        this.modelNumber = modelNumber;
        this.modelName = modelName;
        this.process = process;
        this.TDP = TDP;
        this.wattage = wattage;
        this.tfs = tfs;
        this.clockSpeed = clockSpeed;
        this.rating = rating;
        this.price = price;
        this.manufacturerName = manufacturerName;
        this.generation = generation;
    }

    private Integer id;
    private String modelNumber;

    private String modelName;
    private int process;

    private int TDP;

    private int wattage;

    private float tfs;
    private float clockSpeed;

    private float rating;
    private double price;


    private String manufacturerName;

    private short generation;
}


