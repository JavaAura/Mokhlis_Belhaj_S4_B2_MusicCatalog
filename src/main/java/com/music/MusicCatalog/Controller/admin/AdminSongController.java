package com.music.MusicCatalog.Controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.music.MusicCatalog.DTO.request.SongRequest;
import com.music.MusicCatalog.DTO.response.SongResponse;
import com.music.MusicCatalog.Service.SongService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/songs")
@Tag(name = "Admin Song Controller", description = "Gestion des chansons par l'administrateur")
public class AdminSongController {

    @Autowired
    private SongService songService;

    /**
     * Create a new song
     * @param request The song creation request
     * @return The created song response
     */
    @Operation(
        summary = "Créer une nouvelle chanson",
        description = "Crée une nouvelle chanson avec les informations fournies"
    )
    @ApiResponse(responseCode = "201", description = "Chanson créée avec succès")
    @ApiResponse(responseCode = "400", description = "Corps de la requête invalide - Erreurs de validation" ,
     content = @Content(mediaType = "application/json", schema = @Schema(example = """
        {
            "message": "Validation failed: title: Title is required",
            "status": 400
        }
        """)))
    @PostMapping
    public ResponseEntity<SongResponse> createSong( @Valid @RequestBody SongRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(songService.createSong(request));
    }
}
