package com.pcPartMaker.model;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="RamKit")
public class RamKit {
    @Id
    private String modelNumber;
    @Column(unique = true)
    private short numberOfdimms;
    private String cpuCompatibility;
    private float clockSpeed;
    private Boolean ecc;
    private short wattage;

}