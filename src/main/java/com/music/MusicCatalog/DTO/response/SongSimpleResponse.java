package com.music.MusicCatalog.DTO.response;

import lombok.Data;

@Data
public class SongSimpleResponse {
    private String title;
    private Integer duration;
    private Integer trackNumber;
   
} 