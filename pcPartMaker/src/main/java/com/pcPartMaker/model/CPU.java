package com.pcPartMaker.model;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="cpu")
public class CPU {
    @Id
    private String modelNumber;
    @Column(unique = true)
    private String modelName;
    private String generationName;
    private short numberOfCores;
    private String architecture;
    private Boolean eccCompatibility;
    private float clockSpeed;
    private short TDP;
    private short wattage;



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


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "motherboard_chipset_compatibility",
            joinColumns = @JoinColumn(
                    name = "chipset_model"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "model_number"
            )
    )
    private List<MotherboardChipset> motherboardChipsets;

}
