package com.pcPartMaker.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="memory_type")
@Data
public class MemoryType {
    @Id
    private String ramGeneration;
    private int maxSpeed;
}
