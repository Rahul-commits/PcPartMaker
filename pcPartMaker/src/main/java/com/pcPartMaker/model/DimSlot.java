package com.pcPartMaker.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="DimSlot")
public class DimSlot {
    @Id
    private String slotVariant;

}