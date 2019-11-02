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
import com.roombooking.demo.model.UserModel;
import com.roombooking.demo.repository.UserRepository;

@RestController
public class UserController {
	
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/users")
    public String createUser(@Valid @RequestBody UserModel user) {
    	List<UserModel> existing = userRepository.findByLogin(user.getLogin());
    	if(existing.size() > 0)
    		return "login already exists";
    	userRepository.save(user);
    	return "success";
    }
    
    @GetMapping("/users")
    public List < UserModel > getAllUsers() {
        return userRepository.findAll();
    }
    
    @PutMapping("/users/{id}")
    public ResponseEntity < UserModel > updateUser(@PathVariable(value = "id") Long userId,
        @Valid @RequestBody UserModel userDetails) throws ResourceNotFoundException {
        UserModel user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        user.setName(userDetails.getName());
        user.setSurname(userDetails.getSurname());
        user.setLogin(userDetails.getLogin());
        user.setPassword(userDetails.getPassword());
        final UserModel updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/users/{id}")
    public Map < String, Boolean > deleteUser(@PathVariable(value = "id") Long userId)
    throws ResourceNotFoundException {
        UserModel user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(user);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
