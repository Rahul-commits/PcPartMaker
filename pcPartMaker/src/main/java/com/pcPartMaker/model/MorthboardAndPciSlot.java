package com.pcPartMaker.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "motherboard_pci_slots")
public class MorthboardAndPciSlot implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "motherboard")
    private Motherboard motherboard;

    @Id
    @ManyToOne
    @JoinColumn(name = "slot_type")
    private PciExpressSlotType slotType;

    @Column(name = "quantity")
    private short quantity;


    @Override
    public int hashCode() {
        return motherboard.hashCode() + slotType.hashCode() + quantity;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        MorthboardAndPciSlot other = (MorthboardAndPciSlot) o;
        if (other.motherboard.getModelNumber() == this.motherboard.getModelNumber() &&
                other.slotType.getGeneration() == this.slotType.getGeneration()) return true;
        return false;
    }


}
