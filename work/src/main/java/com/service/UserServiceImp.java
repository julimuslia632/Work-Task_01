package com.service;

import com.domain.Flight;
import com.domain.Trip;
import com.domain.User;
import com.repo.FlightRepo;
import com.repo.TripRepo;
import com.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service  @RequiredArgsConstructor @Transactional @Log4j2
public class UserServiceImp implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final TripRepo tripRepo;
    private final FlightRepo flightRepo;
    @Override
    public Trip addTrip(Trip trip ) {
        log.info("User new Trip is added");
        trip.setTrip_status("Pending");
        return tripRepo.save(trip);
    }



    @Override
    public List<Flight> getFlights() {
        log.info("Showing all available flights");
        return flightRepo.findAll();
    }

    private String getTripStatus(Long id){
        Trip trip = tripRepo.getById(id);
        return trip.getTrip_status();
    }

    @Override
    public void addFlightToUser(String username, Long flight_id , Long trip_id) {
        if (getTripStatus(trip_id).equals("Approved")){
            log.info("Adding flight to the user: {}" ,username);
            User user = userRepo.findByUsername(username);
            Flight flight = flightRepo.getById(flight_id);
            user.getFlights().add(flight);
            Trip trip = tripRepo.getById(trip_id);
            trip.setTrip_status("Pending");

        }else {
            log.info("Flight is denied");
        }

    }


    @Override
    public void addTripToUser(String username, Long trip_id) {
        log.info("Adding trip {} to user {}" , trip_id , username);
        User user = userRepo.findByUsername(username);
        Trip trip = tripRepo.getById(trip_id);
        user.getTrips().add(trip);
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            log.error("User not found in the database");
            throw  new UsernameNotFoundException("User not found in database");
        }else {
            log.error("User found {} in the database" , username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //  We are looping over all the roles  of this user and then for every single one of them we are going
        //  to create a SimpleGrantedAuthority by passing in the role name and adding that to the list(authorities)
        user.getRoles().forEach(roleApp -> authorities.add(new SimpleGrantedAuthority(roleApp.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword() , authorities);
    }
}
