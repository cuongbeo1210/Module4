package com.example.springsecurity.service.impl;

import com.example.springsecurity.model.Role;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repository.IRoleRepository;
import com.example.springsecurity.repository.IUserRepository;
import com.example.springsecurity.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service @Transactional @Slf4j @RequiredArgsConstructor @CrossOrigin("*")
public class IUserServiceImpl implements IUserService, UserDetailsService {
    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IRoleRepository iRoleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserRepository.findByUsername(username);
        if(user == null){
            log.error("Not found");
            throw new UsernameNotFoundException("Not Found");
        } else {
            log.info("User Found :{}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return iUserRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return iRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = iUserRepository.findByUsername(username);
        Role role = iRoleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        return iUserRepository.findByUsername(username);
    }

    @Override
    public Iterable<User> getUsers() {
        return iUserRepository.findAll();
    }

}
