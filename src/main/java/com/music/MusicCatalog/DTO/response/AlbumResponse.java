package com.music.MusicCatalog.DTO.response;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumResponse {
    private String id;
    private String title;
    private String artist;
    private Integer releaseYear;
    private String genre;
    private List<SongResponse> songs;
} 