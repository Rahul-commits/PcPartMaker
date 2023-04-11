package com.pcPartMaker.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "storage")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Storage {

    @Id
    String modelName;

    int capacity;
    // slot type foreign key

    @OneToOne
    @JoinColumn(name = "storage")
    StorageSlotType storageSlotType;

    // parent component relationship
    @OneToOne( fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn( name = "componentId",
            referencedColumnName = "componentId"
    )
    private Component component;

}
