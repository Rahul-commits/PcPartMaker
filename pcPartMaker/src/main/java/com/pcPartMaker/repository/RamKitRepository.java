package com.pcPartMaker.repository;

import com.pcPartMaker.model.Motherboard;
import com.pcPartMaker.model.RamKit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RamKitRepository extends JpaRepository<RamKit, Integer> {
    @Query("SELECT modelName FROM RamKit")
    List<String> findAllRams();

    Optional<RamKit> findByModelNumber(Integer ramId);

    @Query(value = "CALL motherboard_ram_compatibility(:motherboard_id, :ramKit_id);", nativeQuery = true)
    boolean checkMotherboardRam(@Param("motherboard_id") Integer motherboard_id, @Param("ramKit_id")String ramKit_id);
}