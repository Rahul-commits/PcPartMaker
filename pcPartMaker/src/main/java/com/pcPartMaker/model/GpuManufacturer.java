package com.pcPartMaker.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gpu_manufacturer")
public class GpuManufacturer {
    @Id
    private String manufacturerName;
}

