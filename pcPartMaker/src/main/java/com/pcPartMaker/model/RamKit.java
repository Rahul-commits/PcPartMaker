package com.pcPartMaker.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ram_kit")
@Data
@NoArgsConstructor
public class RamKit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String modelName;

    private int moduleAmount;

    private int numberOfDims;
    @Column(unique = true, nullable = false)
    private int modelNumber;
    @ManyToOne
    @JoinColumn (name = "cpu_manufacturer")
    private CpuManufacturer compatibleCpu;

    private int frequency;
    private boolean ecc;

    private float wattage;

    // slot foreign key
    @ManyToOne
    @JoinColumn(name = "dimmSlotType")
    DimmSlotType dimmSlotType;


    // parent component relationship
    @OneToOne( fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn( name = "componentId",
            referencedColumnName = "componentId"
    )
    private Component component;

    @ManyToOne
    private MemoryType memoryType;

    public RamKit(String modelName, int moduleAmount, int numberOfDims, int modelNumber, CpuManufacturer compatibleCpu, int frequency, boolean ecc, float wattage, DimmSlotType dimmSlotType, Component component, MemoryType memoryType) {
        this.modelName = modelName;
        this.moduleAmount = moduleAmount;
        this.numberOfDims = numberOfDims;
        this.modelNumber = modelNumber;
        this.compatibleCpu = compatibleCpu;
        this.frequency = frequency;
        this.ecc = ecc;
        this.wattage = wattage;
        this.dimmSlotType = dimmSlotType;
        this.component = component;
        this.memoryType = memoryType;
    }
}