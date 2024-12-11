package com.music.MusicCatalog.DTO.response;

import lombok.Data;

@Data
public class SongSimpleResponse {
    private String titre;
    private Integer duree;
    private Integer trackNumber;
} 