package com.service;

import com.domain.Flight;
import com.domain.Role;
import com.domain.Trip;
import com.domain.User;
import com.repo.FlightRepo;
import com.repo.RoleRepo;
import com.repo.TripRepo;
import com.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service  @RequiredArgsConstructor @Transactional @Log4j2
public class AdminServiceImp implements AdminService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final TripRepo tripRepo;
    private final FlightRepo flightRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user to the database");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}" , roleName , username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public void updateStatus(String username, Long trip_id, String trip_status) {
        log.info("The request of username : {} is {} by Admin" , username , trip_status);
        User existsUser = userRepo.findByUsername(username);
        Trip existsTrip = tripRepo.getById(trip_id);
        existsTrip.setTrip_status(trip_status);
        existsUser.getTrips().add(existsTrip);
        userRepo.save(existsUser);
    }

    @Override
    public Flight createFlight(Flight flight) {
        log.info("Admin created a flight");

        return  flightRepo.save(flight);
    }
}