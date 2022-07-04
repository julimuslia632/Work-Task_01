package com.service;

import com.domain.Trip;
import com.domain.Flight;

import java.util.List;

public interface UserService {
    Trip addTrip(Trip trip);
    void addTripToUser(String username,Long trip_id);
    List<Flight> getFlights();
    void addFlightToUser(String username, Long flight_id , Long trip_id);

}
