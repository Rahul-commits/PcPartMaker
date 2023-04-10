package com.pcPartMaker.model;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cpu_manufacturer")
@Data
public class CpuManufacturer {
    @Id
    private String manufacturerName;


    public void setManufacturer_name(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
}
