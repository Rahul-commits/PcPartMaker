package com.pcPartMaker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pcPartMaker.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//	@Query("SELECT u FROM Users u WHERE u.username = :username and u.password = :password")
//	User getUser(@Param("username") String username, 
//			@Param("password") String password);

	//@Query("SELECT COUNT(*) FROM Users u WHERE u.username = :username")
	//Integer getUserCount(@Param("username") String username);


	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	@Query(value = "SELECT * FROM Users" , 
			  nativeQuery = true)
	List<User> getAllUsers();
}


