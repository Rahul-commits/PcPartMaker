package com.pcPartMaker.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "motherboard")
public class MemoryType {
        @Id
        private String ramGeneration;
        @Column(unique = true)
        private short MaxBandwidth;
}
