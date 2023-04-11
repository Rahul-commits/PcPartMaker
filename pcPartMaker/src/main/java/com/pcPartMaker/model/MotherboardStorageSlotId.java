package com.pcPartMaker.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MotherboardStorageSlotId implements Serializable {
    private Motherboard motherboard;
    private StorageSlotType storageSlotType;


    public int hashCode() {
        return motherboard.hashCode() + storageSlotType.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (obj.getClass() == this.getClass()) {
            MotherboardStorageSlotId other = (MotherboardStorageSlotId) obj;

            if (other.motherboard.getModelNumber() == this.getMotherboard().getModelNumber() && other.storageSlotType.getSlotInterface() == this.storageSlotType.getSlotInterface()){
                return true;
            }
        }

        return false;
    }

}
