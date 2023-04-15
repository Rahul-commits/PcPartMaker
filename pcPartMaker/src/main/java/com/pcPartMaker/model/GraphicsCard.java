package com.pcPartMaker.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name="graphics_card")
@Data
public class GraphicsCard {
    @Id
    private String modelName;
    private int process;
    private float clockSpeed;

    @ManyToOne
    @JoinColumn(name = "pci_express_slot_type")
    private PciExpressSlotType pciExpressSlotType;
    
    @OneToOne( fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn( name = "componentId",
            referencedColumnName = "componentId"
    )
    private Component component;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name="manufacturer",
            referencedColumnName = "manufacturerName"
            )
    private GpuManufacturer gpuManufacturer;

    public GraphicsCard() {

    }
}
