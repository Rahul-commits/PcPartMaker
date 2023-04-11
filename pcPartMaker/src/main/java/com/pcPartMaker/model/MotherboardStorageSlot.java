package com.pcPartMaker.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="motherboardStorageSlot")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MotherboardStorageSlot {
    @Id
    int something;

    private int quantity;
}
