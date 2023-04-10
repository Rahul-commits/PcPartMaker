package com.pcPartMaker.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "motherboard")
public class Motherboard {
    @Id
    private String modelNumber;
    @Column(unique = true)
    private String productName;
    private boolean eccCompatibility;
    private int wattage;

    @OneToOne( fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn( name = "componentId",
            referencedColumnName = "componentId"
    )
    private Component component;
}
