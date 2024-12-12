package com.music.MusicCatalog.Controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.music.MusicCatalog.DTO.request.AlbumRequest;
import com.music.MusicCatalog.DTO.response.AlbumResponse;
import com.music.MusicCatalog.Service.AlbumService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/albums")
@OpenAPIDefinition(info = @Info(title = "Admin Album API", version = "1.0.0"))
public class AdminAlbumController {
    
    @Autowired
    private  AlbumService albumService;

  

    /**
     * Creates a new album based on the provided request
     * @param request The album creation request
     * @return The created album response
     */
    @Operation(summary = "Create a new album", description = "Creates a new album with the provided information")
    @ApiResponse(responseCode = "200", description = "Album successfully created")
    @ApiResponse(responseCode = "400",
     description = "Invalid request body - Validation errors", 
            content = @Content(mediaType = "application/json", schema = @Schema(example = """
        {
            "message": "Validation failed: title: Title is required",
            "status": 400
        }
        """)))
    @PostMapping
    public AlbumResponse createAlbum(@Valid @RequestBody AlbumRequest request) {
        return albumService.createAlbum(request);
    }


    /**
     * Update an album with the provided information
     * @param id The album ID
     * @param request The album update request
     * @return The updated album response
     */
    @Operation(summary = "modify an album", description = "modifer les informations d'un album")
    @ApiResponse(responseCode = "200", description = "album modifié avec succès")
    @ApiResponse(responseCode = "404", description = "album non trouvé", 
    content = @Content(mediaType = "application/json", schema = @Schema(example = """
        {
            "message": "Album non trouvé",
            "status": 404
        }
        """)))
    @PutMapping("update")
    public AlbumResponse updateAlbum(@Valid @RequestParam String id,  @RequestBody AlbumRequest request) {
        return albumService.updateAlbum(id, request);
    }
}
