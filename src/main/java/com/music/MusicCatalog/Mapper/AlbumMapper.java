package com.music.MusicCatalog.Mapper;


import com.music.MusicCatalog.DTO.request.AlbumRequest;
import com.music.MusicCatalog.DTO.response.AlbumResponse;
import com.music.MusicCatalog.Entity.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {SongMapper.class})
public interface AlbumMapper {

    @Mapping(target = "songs", ignore = true)
    Album toEntity(AlbumRequest request);
    
    @Mapping(target = "songs", source = "songs")
    AlbumResponse toResponse(Album album);

    
} 