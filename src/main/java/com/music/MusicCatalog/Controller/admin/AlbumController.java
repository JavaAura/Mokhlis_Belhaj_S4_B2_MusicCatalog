package com.music.MusicCatalog.Controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.music.MusicCatalog.DTO.request.AlbumRequest;
import com.music.MusicCatalog.DTO.response.AlbumResponse;
import com.music.MusicCatalog.Service.AlbumService;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/admin/albums")
public class AlbumController {
    
    @Autowired
    private  AlbumService albumService;

  

    /**
     * Creates a new album based on the provided request
     * @param request The album creation request
     * @return The created album response
     */
    @PostMapping
    @Operation(
        summary = "Create a new album",
        description = "Creates a new album with the provided information",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Album successfully created",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AlbumResponse.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid request body - Validation errors",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                        example = """
                        {
                            "message": "Validation failed: title: Title is required",
                            "status": 400
                        }
                        """
                    )
                )
            )
        }
    )
    public AlbumResponse createAlbum(@Valid @RequestBody AlbumRequest request) {
        return albumService.createAlbum(request);
    }
}
