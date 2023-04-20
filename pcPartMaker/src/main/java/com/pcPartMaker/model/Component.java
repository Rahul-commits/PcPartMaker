package com.pcPartMaker.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


/**
 * Parent component that owns all the hardware details. including
 * cost rating and price.
 * Example: a graphics card will have a ONE-TO-ONE relationship wit
 * this component class.
 * The contrary example would be CPUManufacturer. It doesn't have a
 * a component parent, because it's just the name of the manufacturer
 * NOT A COMPONENT.
 */
@Entity
@Table(name = "component")
@Data
@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Component {
    @Id
    @SequenceGenerator(
            name = "component_sequence",
            sequenceName = "component_sequence",
            allocationSize = 50
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "component_sequence")
    private int componentId;
    private float rating;
    private double price;


    public int getComponentId() {
        return componentId;
    }

    public float getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }
}