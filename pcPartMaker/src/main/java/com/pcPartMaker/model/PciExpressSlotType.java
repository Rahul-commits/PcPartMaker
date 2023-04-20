package com.pcPartMaker.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "pci_express_slot_type")
public class PciExpressSlotType {
    @Id
    private short generation;
    private double maxBandwidth;

//    @OneToMany(mappedBy = "slotType", cascade = CascadeType.ALL)
//    private Set<MotherboardAndPciSlot> motherboardAndPciSlots = new HashSet<>();

    public short getGeneration() {
        return generation;
    }

    public double getMaxBandwidth() {
        return maxBandwidth;
    }
}