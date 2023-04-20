package com.pcPartMaker.model;


import lombok.Data;
import java.util.Objects;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "motherboard_pci_slots")
public class MotherboardAndPciSlot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   // @Id

//    @EmbeddedId
//    private MotherboardPCISlotId motherboardPCISlotId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
   // @MapsId("motherboardId")
    @JoinColumn(name = "motherboard")
    private Motherboard motherboard;

   // @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
   // @MapsId("slotId")
    @JoinColumn(name = "pciExpressSlotType")
    private PciExpressSlotType slotType;

    @Column(name = "quantity")
    private short quantity;



    @Override
    public int hashCode() {
        return Objects.hash(motherboard.getModelNumber(), slotType.getGeneration(), quantity);
    }
    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        MotherboardAndPciSlot other = (MotherboardAndPciSlot) o;
        if (other.motherboard.getModelNumber() == this.motherboard.getModelNumber() &&
                other.slotType.getGeneration() == this.slotType.getGeneration()) return true;
        return false;
    }

    public int getId() {
        return id;
    }

    public int getMotherboard() {
        return motherboard.getModelNumber();
    }

    public short getSlotType() {
        return slotType.getGeneration();
    }

    public short getQuantity() {
        return quantity;
    }
}