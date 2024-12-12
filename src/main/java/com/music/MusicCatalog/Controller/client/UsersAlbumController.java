package com.music.MusicCatalog.Controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.music.MusicCatalog.DTO.response.AlbumResponse;
import com.music.MusicCatalog.Service.AlbumService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/users/albums")
@OpenAPIDefinition(info = @Info(title = "Users Album API", version = "1.0.0"))
public class UsersAlbumController {
    @Autowired
    private AlbumService albumService;


    /** 
     * Get all albums
     * @param page the page number
     * @param size the page size
     * @param sortBy the field to sort by
     * @param sortOrder the sort order
     * @return the page of albums
     * **/
    @GetMapping
    @Operation(summary = "Get all albums", description = "Retrieves all albums with pagination and sorting options")
    @ApiResponse(responseCode = "200", description = "Albums successfully retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    public Page<AlbumResponse> getAllAlbums(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id") String sortBy,
                                            @RequestParam(defaultValue = "asc") String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        return albumService.getAllAlbums(pageable);
    }

    /** 
     * Get albums by title
     * @param title the title of the album
     * @param page the page number
     * @param size the page size
     * @param sortBy the field to sort by
     * @param sortOrder the sort order
     * @return the page of albums
     * **/
    @GetMapping("/search")
    @Operation(summary = "Get albums by title", description = "Retrieves albums by title with pagination and sorting options")
    @ApiResponse(responseCode = "200", description = "Albums successfully retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    @ApiResponse(responseCode = "404", description = "Albums not found", content = @Content(mediaType = "application/json", schema = @Schema(example = """
        {
            "message": "Albums not found",
            "status": 404
        }
        """)))
    @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content(mediaType = "application/json", schema = @Schema(example = """
        {
            "message": "Invalid request parameters",
            "status": 400
        }
        """)))
    public Page<AlbumResponse> getAlbumsByTitle(@Valid @RequestParam(required = true) String title,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "id") String sortBy,
                                                @RequestParam(defaultValue = "asc") String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        return albumService.getAlbumsByTitle(title, pageable);
    }
}
