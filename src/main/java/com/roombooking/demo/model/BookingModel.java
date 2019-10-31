package com.roombooking.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booking_history")
public class BookingModel {
	 
//	@Id
//    @GeneratedValue
    private long id;
    private long room_id;
    private long user_id;
    private Date date_from;
    private Date date_to;
    
    public BookingModel() {
    	
    }
    
    public BookingModel(long room_id, long user_id, Date date_from, Date date_to) {
    	this.room_id = room_id;
    	this.user_id = user_id;
    	this.date_from = date_from;
    	this.date_to = date_to;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    @Column(name = "room_id", nullable = false)
    public long getRoomId() {
        return room_id;
    }
    
    public void setRoomId(long room_id) {
        this.room_id = room_id;
    }
    
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return user_id;
    }
    
    public void setUserId(long user_id) {
        this.user_id = user_id;
    }
    
    @Column(name = "date_from", nullable = false)
    public Date getDateFrom() {
        return date_from;
    }
    
    public void setDateFrom(Date date_from) {
        this.date_from = date_from;
    }
    
    @Column(name = "date_to", nullable = false)
    public Date getDateTo() {
        return date_to;
    }
    
    public void setDateTo(Date date_to) {
        this.date_to = date_to;
    }
       
    
}