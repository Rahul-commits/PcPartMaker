package com.pcPartMaker.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "pci_express_slot_type")
public class PciExpressSlotType {
    @Id
    private short generation;
    private double maxBandwidth;
}
