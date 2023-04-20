package com.pcPartMaker.repository;

import com.pcPartMaker.model.CPU;
import com.pcPartMaker.model.PciExpressSlotType;
import com.pcPartMaker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PCIExpressSlotTypeRepository extends JpaRepository<PciExpressSlotType, Short> {

    Optional<PciExpressSlotType> findByGeneration(Short generation);
}