package com.pcPartMaker.model;


import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Set;

@Entity
//@Data
@Table(name = "motherboard")
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Getter
@Setter
public class Motherboard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique=true,  nullable = false)
    private int modelNumber;

    private String productName;
    private boolean eccCompatibility;
    private int wattage;

    // dimm slot foreign key
  //  @JsonIgnore
    @ManyToOne
    private DimmSlotType dimmSlotType;
  //  private int dimmSlotNumber;


    //memory type drr3, ddr4, ddr5
    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "memory_type")
    private MemoryType memoryType;

    // parent component
   // @JsonIgnore
    @OneToOne( fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn( name = "componentId",
            referencedColumnName = "componentId"
    )
    private Component component;

   // @JsonIgnore
    @OneToMany(mappedBy = "motherboard", cascade = CascadeType.ALL)
    private Set<MotherboardAndPciSlot> motherboardPCIslots;

    public Motherboard(Integer modelNumber ,String productName, boolean eccCompatibility,
                       int wattage, DimmSlotType dimmSlotType,  MemoryType memoryType,
                       Component component,
                       MotherboardAndPciSlot... motherboardPCIslots) {
        this.modelNumber = modelNumber;
        this.productName = productName;
        this.eccCompatibility = eccCompatibility;
        this.wattage = wattage;
        this.dimmSlotType = dimmSlotType;
        this.memoryType = memoryType;
        this.component = component;
        for(MotherboardAndPciSlot motherboardPCIslot : motherboardPCIslots)
            motherboardPCIslot.setMotherboard(this);
        this.motherboardPCIslots = Stream.of(motherboardPCIslots).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "Motherboard{" +
                "modelNumber=" + modelNumber +
                ", productName='" + productName + '\'' +
                ", eccCompatibility=" + eccCompatibility +
                ", wattage=" + wattage +
                "}";
    }

    public int getModelNumber() {
        return modelNumber;
    }

    public String getProductName() {
        return productName;
    }

    public boolean isEccCompatibility() {
        return eccCompatibility;
    }

    public int getWattage() {
        return wattage;
    }

    public DimmSlotType getDimmSlotType() {
        return dimmSlotType;
    }

    public MemoryType getMemoryType() {
        return memoryType;
    }

    public Component getComponent() {
        return component;
    }

    public Set<MotherboardAndPciSlot> getMotherboardPCIslots() {
        return motherboardPCIslots;
    }
} // so we have dim slot type, in here ... it is used for mapping slot type id.. which is then mapped to ram_kit
  // so we have memory type, in it is used to map to memory type table and table memory type
  // now we need to map pci express slot type, i introduced a new class pci_express_slot_type and now using it..