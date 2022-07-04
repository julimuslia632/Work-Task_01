package com;

import com.domain.Flight;
import com.domain.Role;
import com.domain.Trip;
import com.domain.User;
import com.service.AdminService;
import com.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@SpringBootApplication
public class WorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkApplication.class, args);
	}
	@Bean
	CommandLineRunner run(UserService userService, AdminService adminService ){
		return args -> {


			adminService.saveRole(new Role( null , "Role_User"));
			adminService.saveRole(new Role( null , "Role_Admin"));

			adminService.saveUser(new User(null , "admin" , "admin" , "admin" ,
					new HashSet<>() , new HashSet<>() , new HashSet<>()));
			adminService.saveUser(new User(null , "julian" , "juli" , "juli" ,
					new HashSet<>() , new HashSet<>() , new HashSet<>()));
			adminService.saveUser(new User(null , "user" , "user" , "user" ,
					new HashSet<>() , new HashSet<>() , new HashSet<>()));


			userService.addTrip(new Trip(null,"Event","Event for startups" , " Tirane" , "London" ,
					" 12/12/2022" , "14/01/2022" ,"-"));
			userService.addTrip(new Trip(null,"Work","Work for startups" , " Tirane" , "New Castle" ,
					" 30/7/2023" , "-" ,"-"));


			adminService.addRoleToUser("admin", "Role_Admin");
			adminService.addRoleToUser("juli", "Role_User");



			adminService.createFlight(new Flight(null , "Tirane" , "London" , "12/12/2022" , "01/15/2022"));


		};
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
