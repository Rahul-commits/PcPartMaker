package com.pcPartMaker.model;


import lombok.Data;

import javax.persistence.*;


/*
This is a variant for ram modules or kits.
Only has a relationship with motherboard but number of slots need to be checked;
 */
@Entity
@Data
@Table(name="dimm_slot")
public class DimmSlotType {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    int slotTypeId;

    @Column(unique = true)
    String slotName;

    @Column(name = "description")
    String description;

    public int getSlotTypeId() {
        return slotTypeId;
    }

    public String getSlotName() {
        return slotName;
    }

    public String getDescription() {
        return description;
    }
}