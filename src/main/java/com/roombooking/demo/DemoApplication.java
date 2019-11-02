package com.roombooking.demo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.roombooking.demo.model.RoomModel;
import com.roombooking.demo.model.UserModel;
import com.roombooking.demo.repository.BookingRepository;
import com.roombooking.demo.repository.RoomRepository;
import com.roombooking.demo.repository.UserRepository;

@SpringBootApplication
public class DemoApplication {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}
	
	
	public DemoApplication() {

	}
	
	@Bean
	InitializingBean sendDatabase() {
	    return () -> {
			userRepository.save(new UserModel("John", "Smith", "jsmith", "qwerty"));
			userRepository.save(new UserModel("Jane", "Doe", "jdoe", "mySecret"));
			roomRepository.save(new RoomModel("Large Room", "1st floor", 10, true, "22-22-22-22"));
			roomRepository.save(new RoomModel("Medium Room", "1st floor", 6, true, ""));
			roomRepository.save(new RoomModel("Small Room", "2st floor", 4, false, ""));
	      };
	   }
	
}

