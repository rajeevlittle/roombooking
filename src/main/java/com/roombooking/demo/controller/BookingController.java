package com.roombooking.demo.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roombooking.demo.model.BookingModel;
import com.roombooking.demo.model.RoomModel;
import com.roombooking.demo.model.UserModel;
import com.roombooking.demo.repository.BookingRepository;
import com.roombooking.demo.repository.RoomRepository;
import com.roombooking.demo.repository.UserRepository;
import com.roombooking.demo.request_template.AllBookingList;
import com.roombooking.demo.request_template.NewBooking;
import com.roombooking.demo.response_template.BookingList;

@RestController
public class BookingController {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	public static String encryptString(String input) 
    { 
        try { 
            // getInstance() method is called with algorithm SHA-1 
            MessageDigest md = MessageDigest.getInstance("SHA-1"); 
  
            // digest() method is called 
            // to calculate message digest of the input string 
            // returned as array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
  
            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
  
            // return the HashText 
            return hashtext; 
        } 
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
	
	@RequestMapping("/booking/new")
    public String handleNewBookingRequest(@Valid @RequestBody NewBooking booking_request) {
		
		List<UserModel> booking_user;		
		booking_user = userRepository.findByLogin(booking_request.getUserLogin());
		if(booking_user.size() == 0)
		{
			return "Login not valid.";
		}
		if(this.encryptString(booking_request.getUserPassword()) != booking_user.get(0).getPassword())
		{
			return "Password not valid.";
		}
		
		List<RoomModel> booking_room;
		booking_room = roomRepository.findByRoomName(booking_request.getRoomName());
		if(booking_room.size() == 0)
		{
			return "Invalid Room Name.";
		}
		Date startDate, endDate;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(booking_request.getDateFrom());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return "Wrong date format(Date From)";
		}
		
		try {
			endDate=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(booking_request.getDateTo());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return "Wrong date format(Date To)";
		}
		
		if(startDate.compareTo(endDate) > 0)
		{
			return "Start Date is after the End Date";
		}

		
		
		boolean booking_available;
		booking_available = true;
		List<BookingModel> booking_list;
		booking_list = bookingRepository.findByRoomId(booking_room.get(0).getId());
		for(int i = 0; i < booking_list.size(); i++)
		{
			BookingModel model = booking_list.get(i);
			if(startDate.after(model.getDateFrom()) && startDate.before(model.getDateTo()))
				booking_available = false;
			if(endDate.after(model.getDateFrom()) && endDate.before(model.getDateTo()))
				booking_available = false;
			if(startDate.equals(model.getDateFrom()))
				booking_available = false;
			if(endDate.equals(model.getDateTo()))
				booking_available = false;
		}
		if(!booking_available)
		{
			return "someone else already booked";
		}
		bookingRepository.save(new BookingModel(booking_room.get(0).getId(), booking_user.get(0).getId(), startDate, endDate));
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		String strStartDate = dateFormat.format(startDate);
		return "success";
		
    }
	
	@RequestMapping("/bookings")
    public List<BookingList> getAllBookingList(@Valid @RequestBody AllBookingList booking_request) {
		
		List<BookingList> list = new ArrayList<BookingList>();

		
		
		Date startDate = null, endDate = null;
		
		if(booking_request.getDateFrom() == null) {
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2000-01-01 00:00:00");
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		}
		else {
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(booking_request.getDateFrom());
			} catch (ParseException e) {
				return list;
			}
		}
		
		if(booking_request.getDateTo() == null) {
			try {
				endDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2100-12-31 00:00:00");
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		}
		else {
			
			try {
				endDate=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(booking_request.getDateTo());
			} catch (ParseException e) {
				return list;
			}
		}
			
		List<BookingModel> booking_list;
		booking_list = bookingRepository.findAll();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String temp = dateFormat.format(startDate);
		System.out.println(temp);
		
		for(int i = 0; i < booking_list.size(); i++)
		{
			BookingModel model = booking_list.get(i);
			if(!model.getDateFrom().before(startDate) && !model.getDateTo().after(endDate))
			{
				Optional<UserModel> booking_user;		
				booking_user = userRepository.findById(model.getUserId());
				Optional<RoomModel> booking_room;		
				booking_room = roomRepository.findById(model.getRoomId());
				if(booking_user.isPresent() && booking_room.isPresent())
				{
				
					String strStartDate = dateFormat.format(model.getDateFrom());
					String strEndDate = dateFormat.format(model.getDateTo());
					list.add(new BookingList(booking_room.get().getRoomName(), booking_user.get().getName(), booking_user.get().getSurname(), strStartDate, strEndDate));
				}
				
				
			}
			
				
		}
		
		return list;
	}
	
	@RequestMapping("/bookings/user/{name}")
    public List<BookingList> getBookingListByUserName(@PathVariable(value = "name") String user_name, @Valid @RequestBody AllBookingList booking_request) {
		
		List<BookingList> list = new ArrayList<BookingList>();

		List<UserModel> user;
		user = userRepository.findByName(user_name);
		if(user.size() == 0)
		{
			return list;
		}
		
		Date startDate = null, endDate = null;
		
		if(booking_request.getDateFrom() == null) {
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2000-01-01 00:00:00");
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		}
		else {
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(booking_request.getDateFrom());
			} catch (ParseException e) {
				return list;
			}
		}
		
		if(booking_request.getDateTo() == null) {
			try {
				endDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2100-12-31 00:00:00");
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		}
		else {
			
			try {
				endDate=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(booking_request.getDateTo());
			} catch (ParseException e) {
				return list;
			}
		}
			
		List<BookingModel> booking_list;
		booking_list = bookingRepository.findByUserId(user.get(0).getId());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String temp = dateFormat.format(startDate);
		System.out.println(temp);
		
		for(int i = 0; i < booking_list.size(); i++)
		{
			BookingModel model = booking_list.get(i);
			if(!model.getDateFrom().before(startDate) && !model.getDateTo().after(endDate))
			{
				Optional<UserModel> booking_user;		
				booking_user = userRepository.findById(model.getUserId());
				Optional<RoomModel> booking_room;		
				booking_room = roomRepository.findById(model.getRoomId());
				
				if(booking_user.isPresent() && booking_room.isPresent())
				{
				
					String strStartDate = dateFormat.format(model.getDateFrom());
					String strEndDate = dateFormat.format(model.getDateTo());
					list.add(new BookingList(booking_room.get().getRoomName(), booking_user.get().getName(), booking_user.get().getSurname(), strStartDate, strEndDate));
				}
				
				
			}
			
				
		}
		
		return list;
	}
	
	
	@RequestMapping("/bookings/room/{name}")
    public List<BookingList> getBookingListByRoomName(@PathVariable(value = "name") String room_name, @Valid @RequestBody AllBookingList booking_request) {
		
		List<BookingList> list = new ArrayList<BookingList>();

		List<RoomModel> room;
		room = roomRepository.findByRoomName(room_name);
		if(room.size() == 0)
		{
			return list;
		}
		
		Date startDate = null, endDate = null;
		
		if(booking_request.getDateFrom() == null) {
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2000-01-01 00:00:00");
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		}
		else {
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(booking_request.getDateFrom());
			} catch (ParseException e) {
				return list;
			}
		}
		
		if(booking_request.getDateTo() == null) {
			try {
				endDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2100-12-31 00:00:00");
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		}
		else {
			
			try {
				endDate=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(booking_request.getDateTo());
			} catch (ParseException e) {
				return list;
			}
		}
			
		List<BookingModel> booking_list;
		booking_list = bookingRepository.findByUserId(room.get(0).getId());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String temp = dateFormat.format(startDate);
		System.out.println(temp);
		
		for(int i = 0; i < booking_list.size(); i++)
		{
			BookingModel model = booking_list.get(i);
			if(!model.getDateFrom().before(startDate) && !model.getDateTo().after(endDate))
			{
				Optional<UserModel> booking_user;		
				booking_user = userRepository.findById(model.getUserId());
				Optional<RoomModel> booking_room;		
				booking_room = roomRepository.findById(model.getRoomId());
				
				if(booking_user.isPresent() && booking_room.isPresent())
				{
				
					String strStartDate = dateFormat.format(model.getDateFrom());
					String strEndDate = dateFormat.format(model.getDateTo());
					list.add(new BookingList(booking_room.get().getRoomName(), booking_user.get().getName(), booking_user.get().getSurname(), strStartDate, strEndDate));
				}
				
				
			}
			
				
		}
		
		return list;
	}
	
	
}
