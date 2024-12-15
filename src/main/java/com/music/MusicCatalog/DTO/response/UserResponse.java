package com.music.MusicCatalog.DTO.response;

import java.util.List;

import lombok.Data;

@Data
public class UserResponse {
    private String username;
    private List<String> roles;
}
