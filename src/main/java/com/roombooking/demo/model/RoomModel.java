package com.roombooking.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
public class RoomModel {
	 
//	@Id
//    @GeneratedValue
    private long id;
    private String room_name;
    private String description;
    private int number_of_seats;
    private boolean projector;
    private String phone_number;
    
    public RoomModel() {
    	projector = false;
    }
    
    public RoomModel(String room_name, String description, int number_of_seats, boolean projector, String phone_number) {
    	this.room_name = room_name;
    	this.description = description;
    	this.number_of_seats = number_of_seats;
    	this.projector = projector;
    	this.phone_number = phone_number;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    @Column(name = "room_name", nullable = false, length = 50)
    public String getRoomName() {
        return room_name;
    }
    
    public void setRoomName(String room_name) {
        this.room_name = room_name;
    }
    
    @Column(name = "description", nullable = false, length = 100)
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name = "number_of_seats", nullable = false)
    public int getNumberOfSeats() {
        return number_of_seats;
    }
    
    public void setNumberOfSeats(int number_of_seats) {
        this.number_of_seats = number_of_seats;
    }
    
    @Column(name = "projector", nullable = false)
    public boolean getProjector() {
        return projector;
    }
    
    public void setProjector(boolean projector) {
        this.projector = projector;
    }
    
    @Column(name = "phone_number", nullable = true, length = 100)
    public String getPhoneNumber() {
        return phone_number;
    }
    
    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }
    
    
}