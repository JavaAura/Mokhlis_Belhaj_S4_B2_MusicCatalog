package com.music.MusicCatalog.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.music.MusicCatalog.DTO.request.RoleRequest;
import com.music.MusicCatalog.DTO.response.RoleResponse;
import com.music.MusicCatalog.Entity.Role;
import com.music.MusicCatalog.Exception.ResponseException;
import com.music.MusicCatalog.Mapper.RoleMapper;
import com.music.MusicCatalog.Repository.RoleRepository;
import com.music.MusicCatalog.Service.RoleService;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleResponse> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public RoleResponse createRole(RoleRequest request) {
        // first add prifix ROLE_ to the name
        request.setName("ROLE_" + request.getName());
        // check if the role already exists
        if(roleRepository.findByName(request.getName()).isPresent()){
            throw new ResponseException("Role already exists", HttpStatus.CONFLICT);
        }
        Role role = roleMapper.toEntity(request);
        role = roleRepository.save(role);
        return roleMapper.toResponse(role);
    }

    @Override
    public Role findById(String id) {
        Optional<Role> role = roleRepository.findById(id);
        if (!role.isPresent()) {
            throw new ResponseException("Role not found 1 ", HttpStatus.NOT_FOUND);
        }
        return role.get();
    }

    public Role findByName(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if(role.isPresent()){
            return role.get();
        }
        throw new ResponseException("Role not found", HttpStatus.NOT_FOUND);
    }
}
