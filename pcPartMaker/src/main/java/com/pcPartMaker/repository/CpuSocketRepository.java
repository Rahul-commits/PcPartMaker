package com.pcPartMaker.repository;

import com.pcPartMaker.model.CpuSocketType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CpuSocketRepository extends JpaRepository<CpuSocketType, String> {
    Optional<CpuSocketType> findBySocket(String socket);
}