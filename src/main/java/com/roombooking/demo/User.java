package com.roombooking.demo;

public class User {
	private final long id;
	private final String name;
	private final String surname;
	private final String login;
	private final String password;
	public User(long id, String name, String surname, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

}
