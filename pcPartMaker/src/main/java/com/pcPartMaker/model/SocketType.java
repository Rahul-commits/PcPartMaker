package com.pcPartMaker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "socket_type")
public class SocketType {
    @Id
    private String socket;
    private short number_of_pins;
}

