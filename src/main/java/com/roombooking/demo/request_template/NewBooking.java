package com.roombooking.demo.request_template;



public class NewBooking {
	private final String room_name;
	private final String user_login;
	private final String user_pwd;
	private final String date_from;
	private final String date_to;
	
	public NewBooking(String room_name, String user_login, String user_pwd, String date_from, String date_to) {
		this.room_name = room_name;
		this.user_login = user_login;
		this.user_pwd = user_pwd;
		this.date_from = date_from;
		this.date_to = date_to;
		
	}
	
	public String getRoomName() {
		return room_name;
	}
	
	public String getUserLogin() {
		return user_login;
	}
	
	public String getUserPassword() {
		return user_pwd;
	}
	
	public String getDateFrom() {
		return date_from;
	}
	
	public String getDateTo() {
		return date_to;
	}
	
}
