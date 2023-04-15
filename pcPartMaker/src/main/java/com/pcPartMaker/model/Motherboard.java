package com.pcPartMaker.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "motherboard")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Motherboard {
    @Id
    private int modelNumber;
    @Column(unique = true)
    private String productName;
    private boolean eccCompatibility;
    private int wattage;

    // dimm slot foreign key
    @ManyToOne
    private DimmSlotType dimmSlotType;
    private int dimmSlotNumber;


    //memory type drr3, ddr4, ddr5
    @ManyToOne
    private MemoryType memoryType;

    // parent component
    @OneToOne( fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn( name = "componentId",
            referencedColumnName = "componentId"
    )
    private Component component;
}
