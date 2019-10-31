package com.roombooking.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roombooking.demo.model.RoomModel;


@Repository
public interface RoomRepository extends JpaRepository<RoomModel, Long>{
	List<RoomModel> findByRoomName(String roomName);
}
