package com.pcPartMaker.repository;

import com.pcPartMaker.model.MemoryType;
import com.pcPartMaker.model.MotherboardAndPciSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotherboardAndPCISlotRepository extends JpaRepository<MotherboardAndPciSlot, Integer> {


}