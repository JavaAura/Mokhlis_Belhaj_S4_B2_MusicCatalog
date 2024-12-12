package com.music.MusicCatalog.DTO.response;
import lombok.Data;

@Data
public class SongResponse {
    private String title;
    private Integer duration;
    private Integer trackNumber;
    private AlbumSimpleResponse album;
} 