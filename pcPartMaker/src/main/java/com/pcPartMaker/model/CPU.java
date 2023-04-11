package com.pcPartMaker.model;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="cpu")
public class CPU {
    @Id
    private int modelNumber;
    @Column(unique = true)
    private String modelName;
    private short numberOfCores;
    private String architecture;
    private Boolean eccCompatibility;
    private int frequency;
    private int TDP;
    private int wattage;


    // parent component relationship
    @OneToOne( fetch = FetchType.EAGER,
                optional = false)
    @JoinColumn( name = "componentId",
            referencedColumnName = "componentId"
    )
    private Component component;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_name",
                referencedColumnName = "manufacturerName")
    private CpuManufacturer cpuManufacturer;


    @ManyToOne(fetch = FetchType.LAZY,
                cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "socket",
            referencedColumnName = "socket"
    )
    private CpuSocketType cpuSocketType;
}
