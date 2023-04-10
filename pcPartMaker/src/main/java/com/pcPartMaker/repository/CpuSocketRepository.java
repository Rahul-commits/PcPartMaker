package com.pcPartMaker.repository;

import com.pcPartMaker.model.CpuSocketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpuSocketRepository extends JpaRepository<CpuSocketType, String> {
}
