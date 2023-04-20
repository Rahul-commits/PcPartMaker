package com.pcPartMaker.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="graphics_card")
@Data
@NoArgsConstructor
public class GraphicsCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String modelNumber;

    private String modelName;
    private int process;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name="manufacturer",
            referencedColumnName = "manufacturerName"
    )
    private GpuManufacturer gpuManufacturer;

    @ManyToOne
    @JoinColumn(name = "pci_express_slot_type")
    private PciExpressSlotType pciExpressSlotType;
    private int TDP;

    private int wattage;

    private float tfs;
    private float clockSpeed;

    @OneToOne( fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn( name = "componentId",
            referencedColumnName = "componentId"
    )
    private Component component;

    public GraphicsCard(String modelNumber, String modelName, int process, GpuManufacturer gpuManufacturer, PciExpressSlotType pciExpressSlotType, int TDP, int wattage, float tfs, float clockSpeed, Component component) {
        this.modelNumber = modelNumber;
        this.modelName = modelName;
        this.process = process;
        this.gpuManufacturer = gpuManufacturer;
        this.pciExpressSlotType = pciExpressSlotType;
        this.TDP = TDP;
        this.wattage = wattage;
        this.tfs = tfs;
        this.clockSpeed = clockSpeed;
        this.component = component;
    }
}