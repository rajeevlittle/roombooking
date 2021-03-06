package com.roombooking.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roombooking.demo.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
	List<UserModel> findByLogin(String login);
	List<UserModel> findByName(String name);
}
