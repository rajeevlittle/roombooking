package com.roombooking.demo.response_template;

public class BookingList {
	private  String room_name;
	private  String user_name;
	private  String user_surname;
	private  String start_date;
	private  String end_date;
	
	public BookingList() {
		
	}
	
	public BookingList(String room_name, String user_name, String user_surname, String start_date, String end_date)
	{
		this.room_name = room_name;
		this.user_name = user_name;
		this.user_surname = user_surname;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	
	public String getRoomName()
	{
		return room_name;
	}
	public void setRoomName(String room_name) {
		this.room_name = room_name;
	}
	
	public String getUserName() {
		return user_name;
	}
	
	public void setUserName(String user_name) {
		this.user_name = user_name;
	}
	
	public String getUserSurname() {
		return user_surname;
	}
	
	public void setUserSurname(String user_surname) {
		this.user_surname = user_surname;
	}
	
	public String getStartDate() {
		return start_date;
	}
	
	public void setStartDate(String start_date) {
		this.start_date = start_date;
	}
	
	public String getEndDate() {
		return end_date;
	}
	
	public void setEndDate(String end_date) {
		this.end_date = end_date;
	}
	
}
