package com.pcPartMaker.repository;

import com.pcPartMaker.model.CPU;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpuRepository extends JpaRepository<CPU, String> {
}
