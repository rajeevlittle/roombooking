package com.roombooking.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roombooking.demo.ResourceNotFoundException;
import com.roombooking.demo.model.RoomModel;
import com.roombooking.demo.repository.RoomRepository;

@RestController
public class RoomController {
	
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private RoomRepository roomRepository;

    @RequestMapping("/rooms")
    public RoomModel createRoom(@Valid @RequestBody RoomModel room) {
    	return roomRepository.save(room);
    }
    
    @GetMapping("/rooms")
    public List < RoomModel > getAllRooms() {
        return roomRepository.findAll();
    }
    
    @PutMapping("/rooms/{id}")
    public ResponseEntity < RoomModel > updateUser(@PathVariable(value = "id") Long userId,
        @Valid @RequestBody RoomModel roomDetails) throws ResourceNotFoundException {
    	RoomModel room = roomRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Room not found for this id :: " + userId));
    	

        room.setRoomName(roomDetails.getRoomName());
        room.setDescription(roomDetails.getDescription());
        room.setProjector(roomDetails.getProjector());
        room.setNumberOfSeats(roomDetails.getNumberOfSeats());
        final RoomModel updatedRoom = roomRepository.save(room);
        return ResponseEntity.ok(updatedRoom);
    }
    @DeleteMapping("/rooms/{id}")
    public Map < String, Boolean > deleteUser(@PathVariable(value = "id") Long roomId)
    throws ResourceNotFoundException {
        RoomModel room = roomRepository.findById(roomId)
            .orElseThrow(() -> new ResourceNotFoundException("Room not found for this id :: " + roomId));

        roomRepository.delete(room);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
