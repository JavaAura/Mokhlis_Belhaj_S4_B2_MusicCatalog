package com.music.MusicCatalog.Mapper;


import com.music.MusicCatalog.DTO.request.AlbumRequest;
import com.music.MusicCatalog.DTO.response.AlbumResponse;
import com.music.MusicCatalog.Entity.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface AlbumMapper {

    @Mapping(target = "id", ignore = true)
    Album toEntity(AlbumRequest request);
    
    AlbumResponse toResponse(Album album);
} 