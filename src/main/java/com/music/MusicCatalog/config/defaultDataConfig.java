package com.music.MusicCatalog.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.music.MusicCatalog.DTO.request.UserRequest;
import com.music.MusicCatalog.Entity.Role;
import com.music.MusicCatalog.Entity.Users;
import com.music.MusicCatalog.Repository.RoleRepository;
import com.music.MusicCatalog.Repository.UserRepository;
import com.music.MusicCatalog.Service.UserService;


@Configuration
public class defaultDataConfig {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Order(1)
    @Bean
    public boolean defaultData(RoleRepository roleRepository) {
        if(roleRepository.findByName("ROLE_USER").isEmpty()) {
            Role role = new Role();
            role.setName("ROLE_USER");
            roleRepository.save(role);
           
        }

        if(roleRepository.findByName("ROLE_ADMIN").isEmpty()) {

        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
            roleRepository.save(roleAdmin);
        }
        return true;
    }

    @Order(2)
    @Bean
    public boolean defaultAdmin(UserRepository userRepository, UserService userService) {
        if(userRepository.findByUsername("admin").isEmpty()) {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("admin");
        userRequest.setPassword("admin123");
        userService.registerUser(userRequest);
        assignRoleToAdmin(userRepository, userService, roleRepository);
        return true;
        }
        return false;
    }

    
    public boolean   assignRoleToAdmin(UserRepository userRepository, UserService userService, RoleRepository roleRepository) {
         Users user = userRepository.findByUsername("admin").get();
        Role role = roleRepository.findByName("ROLE_ADMIN").get();
        userService.assignRoleToUser(user.getId(), role.getId());
        return true;
    }

    
}

