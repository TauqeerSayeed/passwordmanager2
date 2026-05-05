package com.example.passwordmanager;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class PasswordManagerApplication {


	public static void main(String[] args) {
		SpringApplication.run(PasswordManagerApplication.class, args);
	}

	@PostConstruct
	public void init(){
		// Set default timezone
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		System.out.println("Application running in timezone: " + TimeZone.getDefault().getID());
	}

}
