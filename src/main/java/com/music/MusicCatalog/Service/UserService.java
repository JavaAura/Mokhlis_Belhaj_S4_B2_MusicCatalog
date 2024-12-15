package com.music.MusicCatalog.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.music.MusicCatalog.DTO.request.UserRequest;
import com.music.MusicCatalog.DTO.response.UserResponse;

public interface UserService {
    
    public Page<UserResponse> getAllUsers(Pageable pageable);
    public UserResponse registerUser(UserRequest request);
    public void deleteUser(String id);
    public UserResponse assignRoleToUser(String userId, String roleId);
    public void desassignRoleToUser(String userId, String roleId);
}
