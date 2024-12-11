package com.music.MusicCatalog.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "albums")
public class Album {
    @Id
    private String id;
    private String title;
    private String artist;
    private Integer releaseYear;
    private String genre;
} 