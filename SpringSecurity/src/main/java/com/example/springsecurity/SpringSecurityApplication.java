package com.example.springsecurity;

import com.example.springsecurity.model.Role;
import com.example.springsecurity.model.User;
import com.example.springsecurity.service.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CommandLineRunner run(IUserService iUserService){
//        return args -> {
////            Role role = new Role(null, "Admin");
////            Role role1 = new Role(null, "User");
////            iUserService.saveRole(role);
//            iUserService.saveRole(new Role(null, "User"));
//            iUserService.saveRole(new Role(null, "Admin"));
//            iUserService.saveRole(new Role(null, "Manager"));
////            Collection<Role> roles = new ArrayList<>();
////            roles.add(role);
////            roles.add(role1);
//            iUserService.saveUser(new User(null, "Cuongbeo", "cuongbeo", "12345", new ArrayList<>()));
//            iUserService.saveUser(new User(null, "Nambeo", "nambeo", "12345", new ArrayList<>()));
//            iUserService.saveUser(new User(null, "Trungbeo", "trungbeo", "12345", new ArrayList<>()));
////            iUserService.addRoleToUser("cuongbeo", role.getName());
//
//            iUserService.addRoleToUser("cuongbeo", "User");
//            iUserService.addRoleToUser("cuongbeo", "Admin");
//            iUserService.addRoleToUser("nambeo", "User");
//            iUserService.addRoleToUser("trungbeo", "Admin");
//        };
//    }
}


