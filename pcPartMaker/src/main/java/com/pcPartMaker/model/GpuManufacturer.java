package com.pcPartMaker.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gpu_manufacturer")
@Data
@NoArgsConstructor
public class GpuManufacturer {
    @Id
    private String manufacturerName;

    public GpuManufacturer(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
}
