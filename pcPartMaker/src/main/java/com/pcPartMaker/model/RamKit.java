package com.pcPartMaker.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="ramKit")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RamKit {

    @Id
    String modelName;

    int moduleAmount;

    @OneToOne
    CpuManufacturer compatibleCpu;

    int frequency;
    boolean ecc;

    // slot foreign key
    @OneToOne
    @JoinColumn(name = "dimmSlotType")
    DimmSlotType dimmSlotType;


    // parent component relationship
    @OneToOne( fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn( name = "componentId",
            referencedColumnName = "componentId"
    )
    private Component component;
}
