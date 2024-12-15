package com.music.MusicCatalog.Mapper;

import org.mapstruct.Mapper;

import com.music.MusicCatalog.DTO.request.RoleRequest;
import com.music.MusicCatalog.DTO.response.RoleResponse;
import com.music.MusicCatalog.Entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    public RoleResponse toResponse(Role role);

    public Role toEntity(RoleRequest request);



}
