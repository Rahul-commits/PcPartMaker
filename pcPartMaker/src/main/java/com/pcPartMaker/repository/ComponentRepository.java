package com.pcPartMaker.repository;

import com.pcPartMaker.model.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ComponentRepository  extends JpaRepository<Component, Integer> {
}
