package com.api;

import com.domain.Role;
import com.domain.User;
import com.domain.Flight;
import com.service.AdminService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminResource {
    private final AdminService adminService;
    @GetMapping("/show-users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(adminService.getUsers());
    }

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username){
        return ResponseEntity.ok().body(adminService.getUser(username));
    }

    @PostMapping("/save-user")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/save-user").toUriString());
        return ResponseEntity.created(uri).body(adminService.saveUser(user));
    }

    @PostMapping("/save-role")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/save-role").toUriString());
        return ResponseEntity.created(uri).body(adminService.saveRole(role));
    }

    @PostMapping("/add-role-to-user")
    public ResponseEntity<?> addRoleToUser(@RequestBody UserFrom form){
        adminService.addRoleToUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update-status")
    public ResponseEntity<User> status(@RequestBody UserFrom form){
        adminService.updateStatus(form.getUsername() ,form.getTrip_id() , form.getStatus());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create-flight")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/create-flight").toUriString());
        return  ResponseEntity.created(uri).body(adminService.createFlight(flight));
    }

    @Data
    static
    class UserFrom{
        private String username;
        private String roleName;
        private Long trip_id;
        private String status;
    }
}

