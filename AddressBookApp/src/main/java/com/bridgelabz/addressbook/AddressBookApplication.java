package com.bridgelabz.addressbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Use @SpringBootApplication Annotation
@SpringBootApplication
//Create  a class AddressBookApplication to Start the application
public class AddressBookApplication {
	public static void main(String[] args) {
		//Call the method to run the application server
		SpringApplication.run(AddressBookApplication.class, args);
	}
}
