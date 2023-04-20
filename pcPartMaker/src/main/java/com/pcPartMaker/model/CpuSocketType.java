package com.pcPartMaker.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "socket_type")
@Data
public class CpuSocketType implements Serializable {
    @Id
    private String socket;
    private short number_of_pins;

    public String getSocket() {
        return socket;
    }

    public short getNumber_of_pins() {
        return number_of_pins;
    }
}