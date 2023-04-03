package com.pcPartMaker.model;


import javax.persistence.*;

@Entity
@Table(name = "component")
public class Component {
    @Id
    @SequenceGenerator(
            name = "component_sequence",
            sequenceName = "component_sequence",
            allocationSize = 50
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "component_sequence")
    private Long componentId;
    private float rating;
    private double price;
}
