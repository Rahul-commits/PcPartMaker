package com.pcPartMaker.repository;

import com.pcPartMaker.model.Motherboard;
import com.pcPartMaker.model.RamKit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RamKitRepository extends JpaRepository<RamKit, Integer> {
    @Query("SELECT modelName FROM RamKit")
    List<String> findAllRams();

    Optional<RamKit> findByModelNumber(Integer ramId);
}