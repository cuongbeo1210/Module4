package com.example.springsecurity.controller;


import com.example.springsecurity.model.Product;
import com.example.springsecurity.model.Role;
import com.example.springsecurity.model.User;
import com.example.springsecurity.service.ICategoryService;
import com.example.springsecurity.service.IProductService;
import com.example.springsecurity.service.IUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserController {
    @Autowired
    private IUserService iUserServiceImpl;

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> showAllUsers(){
        return ResponseEntity.ok().body(iUserServiceImpl.getUsers());
    }
    @PostMapping("user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/save").toUriString());
        return ResponseEntity.created(uri).body(iUserServiceImpl.saveUser(user));
    }
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/save").toUriString());
        return ResponseEntity.created(uri).body(iUserServiceImpl.saveRole(role));
    }
    @PostMapping("role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm){
        iUserServiceImpl.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getPassword());
        return ResponseEntity.ok().build();
    }

    @Data
    static
    class RoleToUserForm{
        private String username;
        private String password;
    }
}


