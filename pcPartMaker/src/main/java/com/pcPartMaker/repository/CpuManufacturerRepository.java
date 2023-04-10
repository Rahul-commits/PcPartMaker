package com.pcPartMaker.repository;

import com.pcPartMaker.model.CpuManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CpuManufacturerRepository extends JpaRepository<CpuManufacturer, String> {
}
