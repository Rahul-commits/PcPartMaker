package com.pcPartMaker.repository;

import com.pcPartMaker.model.CPU;
import com.pcPartMaker.model.MemoryType;
import com.pcPartMaker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemoryTypeRepository extends JpaRepository<MemoryType, Integer> {

    Optional<MemoryType> findByMemoryTypeId(int memoryTypeId);
    Optional<MemoryType> findByRamGeneration(String ramGeneration);


}