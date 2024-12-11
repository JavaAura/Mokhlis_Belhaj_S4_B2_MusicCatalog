package com.music.MusicCatalog.DTO.response;

import lombok.Data;

@Data
public class AlbumSimpleResponse {
    private String titre;
    private String artiste;
    private Integer annee;
} 