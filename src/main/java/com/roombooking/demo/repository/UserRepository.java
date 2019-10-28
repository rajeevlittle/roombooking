package com.roombooking.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roombooking.demo.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
	
}
