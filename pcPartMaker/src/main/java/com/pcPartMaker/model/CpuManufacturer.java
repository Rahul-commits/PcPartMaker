package com.pcPartMaker.model;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cpu_manufacturer")
@Data
@NoArgsConstructor
public class CpuManufacturer {
    @Id
    private String manufacturerName;

    public CpuManufacturer(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }


//    public void setManufacturer_name(String manufacturerName) {
//        this.manufacturerName = manufacturerName;
//    }
}