package com.music.MusicCatalog.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SongRequest {
    @NotBlank(message = "Le titre est obligatoire")
    private String titre;
    
    @NotNull(message = "La durée est obligatoire")
    @Min(value = 1, message = "La durée doit être supérieure à 0")
    private Integer duree;
    
    @NotNull(message = "Le numéro de piste est obligatoire")
    @Min(value = 1, message = "Le numéro de piste doit être supérieur à 0")
    private Integer trackNumber;
    
    @NotNull(message = "L'ID de l'album est obligatoire")
    private String albumId;
} 