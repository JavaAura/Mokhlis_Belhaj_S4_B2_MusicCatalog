package com.music.MusicCatalog.DTO.response;
import lombok.Data;

@Data
public class SongResponse {
    private String titre;
    private Integer duree;
    private Integer trackNumber;
    private AlbumSimpleResponse album;
} 