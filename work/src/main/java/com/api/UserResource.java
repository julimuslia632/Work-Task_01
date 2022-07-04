package com.api;

import com.domain.Flight;
import com.domain.Trip;
import com.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;


    @PostMapping("/add-trip")
    public Trip addTrip(@RequestBody Trip trip){
        return userService.addTrip(trip);
    }


    @PostMapping("/addTripToUser")
    public ResponseEntity<?> addTripToUser(@RequestBody TripToUserFrom form){
        userService.addTripToUser(form.getUsername(),form.getTrip_id());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-flight")
    public ResponseEntity<Flight> addFlight(@RequestBody TripToUserFrom form){
        userService.addFlightToUser(form.getUsername() , form.getFlight_id() , form.getTrip_id());
        return ResponseEntity.ok().build();
    }
    @Data
    static
    class TripToUserFrom{
        private String username;
        private Long trip_id;
        private Long flight_id;
    }

}
