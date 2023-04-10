package com.pcPartMaker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;


// DISCARD THIS ENTITY, NOT ENOUGH DATA
@Entity
@Table(name="motherboard_chipset")
public class MotherboardChipset {
    @Id
    private String chipsetModel;
}
