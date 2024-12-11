package com.music.MusicCatalog.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Data
@Document(collection = "songs")
public class Song {
    @Id
    private String id;
    @NotBlank(message = "Le titre ne peut pas être vide")
    private String title;
    @PositiveOrZero(message = "La durée doit être positive ou zéro")
    private Integer duration;
    @PositiveOrZero(message = "Le numéro de piste doit être positif ou zéro")
    private Integer trackNumber;
    
    @NotNull(message = "Une chanson doit appartenir à un album")
    @DBRef
    private Album album;
} 