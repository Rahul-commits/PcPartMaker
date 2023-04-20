package com.pcPartMaker.repository;

import java.util.List;
import java.util.Optional;

import com.pcPartMaker.model.CPU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CpuRepository extends JpaRepository<CPU, Integer> {

    List<CPU> findAll();

    Optional<CPU> findByModelNumber(Integer modelNumber);
}