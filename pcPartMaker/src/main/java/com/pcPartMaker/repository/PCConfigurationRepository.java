package com.pcPartMaker.repository;

import com.pcPartMaker.model.PCConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PCConfigurationRepository extends JpaRepository<PCConfiguration, Integer> {
//    @Query(value = "SELECT * FROM user_pc_config config WHERE config.username=?1" ,
//            nativeQuery = true)
    @Query("SELECT config FROM PCConfiguration config WHERE config.user.username = :username" )
    List<PCConfiguration> findByUsernameIn(String username);

}
