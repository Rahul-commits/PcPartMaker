package com.pcPartMaker.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pci_express_slot_type")
public class PciExpressSlotType {
    @Id
    private short generation;

    private short numberOfLanes;
    private double maxBandwidth;
}
