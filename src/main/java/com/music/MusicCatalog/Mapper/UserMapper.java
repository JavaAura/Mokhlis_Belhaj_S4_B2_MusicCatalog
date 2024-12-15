package com.music.MusicCatalog.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.music.MusicCatalog.DTO.request.UserRequest;
import com.music.MusicCatalog.DTO.response.UserResponse;
import com.music.MusicCatalog.Entity.Users;
import com.music.MusicCatalog.Entity.Role;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(rolesToRoleNames(user.getRoles()))")
    public abstract UserResponse toResponse(Users user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "roles", ignore = true)
    public abstract Users toEntity(UserRequest request);

    default List<String> rolesToRoleNames(List<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toList());
    }
}
