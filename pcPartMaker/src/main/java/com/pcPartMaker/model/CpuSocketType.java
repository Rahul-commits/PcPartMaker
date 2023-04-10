package com.pcPartMaker.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "socket_type")
@Data
public class CpuSocketType {
    @Id
    private String socket;
    private short number_of_pins;
}

