package com.roombooking.demo.request_template;

public class AllBookingList {
	private final String date_from;
	private final String date_to;
	
	public AllBookingList(String date_from, String date_to) {
		this.date_from = date_from;
		this.date_to = date_to;
	}
	
	public String getDateFrom() {
		return date_from;
	}
	
	public String getDateTo() {
		return date_to;
	}
}
