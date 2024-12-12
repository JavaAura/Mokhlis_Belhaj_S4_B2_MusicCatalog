package com.music.MusicCatalog.DTO.response;

import lombok.Data;

@Data
public class AlbumSimpleResponse {
    private String title;
    private String artist;
    private Integer releaseYear;
} 