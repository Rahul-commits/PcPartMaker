package com.pcPartMaker.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="PowerSupplyUnit")
public class PowerSupplyUnit {

    @Id
    private String modelNumber;
    @Column(unique = true)
    private String modelName;
    private int FormFactor;
    private short PowerOutput;
    private String modularity;
    private String EcoMode;
    private float EfficiencyRating;


    }