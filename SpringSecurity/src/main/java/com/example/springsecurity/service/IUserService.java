package com.example.springsecurity.service;

import com.example.springsecurity.model.Role;
import com.example.springsecurity.model.User;

public interface IUserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    Iterable<User> getUsers();
}
