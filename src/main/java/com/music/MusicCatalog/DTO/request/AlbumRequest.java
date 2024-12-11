package com.music.MusicCatalog.DTO.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumRequest {
    
    @NotBlank(message = "Le titre est obligatoire")
    private String title;
    
    @NotBlank(message = "L'artiste est obligatoire")
    private String artist;
    
    @NotNull(message = "L'année de sortie est obligatoire")
    private Integer releaseYear;
    
    private String genre;
} 