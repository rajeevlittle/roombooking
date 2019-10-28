package com.roombooking.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class UserModel {
	 
    @Id
    @GeneratedValue
    private Long id;
    private String name;
 

}