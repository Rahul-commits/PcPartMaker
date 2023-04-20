package com.pcPartMaker.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Entity

@Data
@Table(name="user_pc_config")
@NoArgsConstructor
public class PCConfiguration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ram;
    private String motherboard;
    private String cpu;

    private String graphicsCard;
    @ManyToOne
    @JoinColumn(name="username", referencedColumnName="username")
    private User user;

    public PCConfiguration(String ram, String motherboard, String cpu, User user, String graphicsCard) {
        this.id = id;
        this.ram = ram;
        this.motherboard = motherboard;
        this.cpu = cpu;
        this.user = user;
        this.graphicsCard = graphicsCard;
    }
}





