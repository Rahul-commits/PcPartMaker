package com.pcPartMaker.model;
import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "cpu_manufacturer")
public class CpuManufacturer {
    @Id
    private String manufacturerName;


    public String getManufacturer_name() {
        return manufacturerName;
    }

    public void setManufacturer_name(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
}
