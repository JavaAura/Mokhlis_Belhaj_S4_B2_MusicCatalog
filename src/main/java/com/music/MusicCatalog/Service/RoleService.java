package com.music.MusicCatalog.Service;

import java.util.List;

import com.music.MusicCatalog.DTO.request.RoleRequest;
import com.music.MusicCatalog.DTO.response.RoleResponse;
import com.music.MusicCatalog.Entity.Role;

public interface RoleService {
    
    public List<RoleResponse> getAllRoles();
    public RoleResponse createRole(RoleRequest request);
    public Role findById(String id);
    public Role findByName(String name);
}
