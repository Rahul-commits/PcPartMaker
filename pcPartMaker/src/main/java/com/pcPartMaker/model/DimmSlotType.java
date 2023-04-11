package com.pcPartMaker.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/*
This is a variant for ram modules or kits.
Only has a relationship with motherboard but number of slots need to be checked;
 */
@Entity
@Table(name="dimmSlotType")
public class DimmSlotType {
    @Id
    String slotVariant;
}
