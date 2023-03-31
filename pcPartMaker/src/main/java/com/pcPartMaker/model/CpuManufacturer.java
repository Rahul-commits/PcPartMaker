package com.pcPartMaker.model;
import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "cpu_manufacturer",uniqueConstraints = {
        @UniqueConstraint(columnNames = "manufacturer_name")
}
)
public class CpuManufacturer {
    @Id
    private String manufacturer_name;


    public String getManufacturer_name() {
        return manufacturer_name;
    }

    public void setManufacturer_name(String manufacturer_name) {
        this.manufacturer_name = manufacturer_name;
    }
}
