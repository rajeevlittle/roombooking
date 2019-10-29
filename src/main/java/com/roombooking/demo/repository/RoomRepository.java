package com.roombooking.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roombooking.demo.model.RoomModel;

@Repository
public interface RoomRepository extends JpaRepository<RoomModel, Long>{
	
}
