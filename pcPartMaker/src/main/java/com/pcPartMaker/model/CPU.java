package com.pcPartMaker.model;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity

@Data
@Table(name="cpu")
@NoArgsConstructor
public class CPU implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private int modelNumber;

    private String modelName;
    private short numberOfCores;
    private String architecture;
    private Boolean eccCompatibility;
    private int frequency;
    private int TDP;
    private int wattage;

    private int clock;
    // parent component relationship
    @OneToOne( fetch = FetchType.EAGER,
            optional = false, cascade = CascadeType.ALL)
    @JoinColumn( name = "componentId",
            referencedColumnName = "componentId"
    )
    private Component component;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_name",
            referencedColumnName = "manufacturerName")
    private CpuManufacturer cpuManufacturer;


    @ManyToOne
    private CpuSocketType cpuSocketType;

    @ManyToOne
    private MemoryType memoryType;

    public CPU(int modelNumber, String modelName, short numberOfCores, String architecture, Boolean eccCompatibility, int frequency, int TDP, int wattage, Component component, CpuManufacturer cpuManufacturer, CpuSocketType cpuSocketType, int clock,
               MemoryType memoryType) {
        this.modelNumber = modelNumber;
        this.modelName = modelName;
        this.numberOfCores = numberOfCores;
        this.architecture = architecture;
        this.eccCompatibility = eccCompatibility;
        this.frequency = frequency;
        this.TDP = TDP;
        this.wattage = wattage;
        this.component = component;
        this.cpuManufacturer = cpuManufacturer;
        this.cpuSocketType = cpuSocketType;
        this.clock = clock;
        this.memoryType = memoryType;
    }
}