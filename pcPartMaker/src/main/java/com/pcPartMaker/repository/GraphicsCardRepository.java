package com.pcPartMaker.repository;

import com.pcPartMaker.model.GraphicsCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GraphicsCardRepository extends JpaRepository<GraphicsCard, Integer> {
    Optional<GraphicsCard> findByModelNumber(String modelNumber);
}