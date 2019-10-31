package com.roombooking.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roombooking.demo.model.BookingModel;



@Repository
public interface BookingRepository extends JpaRepository<BookingModel, Long>{
	List<BookingModel> findByRoomId(long room_id);
	List<BookingModel> findByUserId(long user_id);
}
