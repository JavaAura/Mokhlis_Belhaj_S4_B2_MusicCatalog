package com.music.MusicCatalog.Service.Impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.music.MusicCatalog.DTO.request.UserRequest;
import com.music.MusicCatalog.DTO.response.RoleResponse;
import com.music.MusicCatalog.DTO.response.UserResponse;
import com.music.MusicCatalog.Entity.Role;
import com.music.MusicCatalog.Entity.Users;
import com.music.MusicCatalog.Exception.ResponseException;
import com.music.MusicCatalog.Mapper.UserMapper;
import com.music.MusicCatalog.Repository.UserRepository;
import com.music.MusicCatalog.Service.RoleService;
import com.music.MusicCatalog.Service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserMapper userMapper;  

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toResponse);
    }

    @Override
    public UserResponse registerUser(UserRequest request) {
    
    	
        Users user = userMapper.toEntity(request);
        
        Role role = roleService.findByName("ROLE_USER");
        user.setRoles(Collections.singletonList(role));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse assignRoleToUser(String userId, String roleId) {
        Optional<Users> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new ResponseException("User not found ", HttpStatus.NOT_FOUND);
        }
        Role role = roleService.findById(roleId);
        
        if (user.get().getRoles().contains(role)) {
            throw new ResponseException("User already has role", HttpStatus.BAD_REQUEST);
        }
        user.get().getRoles().add(role);
        return userMapper.toResponse(userRepository.save(user.get()));
    }

    @Override
    public void desassignRoleToUser(String userId, String roleId) {
        Optional<Users> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new ResponseException("User not found", HttpStatus.NOT_FOUND);
        }
        Role role = roleService.findById(roleId);
       
        // cheque si user has role
        if (!user.get().getRoles().contains(role)) {
            throw new ResponseException("User does not have role", HttpStatus.BAD_REQUEST);
        }
        user.get().getRoles().remove(role);
        userRepository.save(user.get());
    }
}
