package com.pcPartMaker.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="psu")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PSU {
    @Id
    String modelName;

    String formFactor;

    int maxPowerOutput;

    int rating;

    boolean modularity;
    boolean ecoMode;


    // parent component relationship
    @OneToOne( fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn( name = "componentId",
            referencedColumnName = "componentId"
    )
    private Component component;
}
