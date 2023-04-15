package com.pcPartMaker.model;


import javax.persistence.*;


/*
This is a variant for ram modules or kits.
Only has a relationship with motherboard but number of slots need to be checked;
 */
@Entity
@Table(name="dimm_slot")
public class DimmSlotType {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    int slotTypeId;

    @Column(unique = true)
    String slotName;

    @Column(name = "description")
    String description;
}
