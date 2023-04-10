package com.pcPartMaker.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gpu_manufacturer")
@Data
public class GpuManufacturer {
    @Id
    private String manufacturerName;
}

