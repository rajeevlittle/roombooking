package com.roombooking.demo.controller;

import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roombooking.demo.model.UserModel;
import com.roombooking.demo.repository.UserRepository;

@RestController
public class UserController {
	
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/greeting")
    public UserModel greeting(@Valid @RequestBody UserModel user) {
//    	return body;
//    	return user;
    	return userRepository.save(user);
//    	return body.getName();
    }
}