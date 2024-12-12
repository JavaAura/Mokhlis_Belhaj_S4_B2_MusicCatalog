package com.music.MusicCatalog.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "albums")
public class Album {
    @Id
    private String id;
    @NotBlank(message = "Le titre est obligatoire") 
    private String title;
    @NotBlank(message = "L'artiste est obligatoire")
    private String artist;
    @NotNull(message = "La date de sortie est obligatoire")
    private Integer releaseYear;
    @NotBlank(message = "Le genre est obligatoire")
    private String genre;
    @DBRef
    private List<Song> songs;
} 