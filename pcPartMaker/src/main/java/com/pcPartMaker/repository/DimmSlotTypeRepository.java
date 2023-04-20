package com.pcPartMaker.repository;

import com.pcPartMaker.model.CPU;
import com.pcPartMaker.model.DimmSlotType;
import com.pcPartMaker.model.MemoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DimmSlotTypeRepository extends JpaRepository<DimmSlotType, Integer> {

    Optional<DimmSlotType> findBySlotTypeId(int slotTypeId);

    Optional<DimmSlotType> findBySlotName(String slotName);

}