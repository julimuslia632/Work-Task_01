package com.service;

import com.domain.Flight;
import com.domain.Role;
import com.domain.User;

import java.util.List;

public interface AdminService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User> getUsers();
    void updateStatus(String username , Long trip_id , String status);
    Flight createFlight(Flight flight);
}
