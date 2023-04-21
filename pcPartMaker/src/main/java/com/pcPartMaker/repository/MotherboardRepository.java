package com.pcPartMaker.repository;

import com.pcPartMaker.model.Motherboard;
import com.pcPartMaker.model.PciExpressSlotType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MotherboardRepository extends JpaRepository<Motherboard, Integer> {

    @Query("SELECT productName FROM Motherboard")
    List<String> findAllMbs();

    Optional<Motherboard> findByModelNumber(Integer modelNumber);

    List<Motherboard> findByProductName(String motherboard);
}