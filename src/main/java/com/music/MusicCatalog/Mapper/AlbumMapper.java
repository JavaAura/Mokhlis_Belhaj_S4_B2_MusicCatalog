package com.music.MusicCatalog.Mapper;


import com.music.MusicCatalog.DTO.request.AlbumRequest;
import com.music.MusicCatalog.DTO.response.AlbumResponse;
import com.music.MusicCatalog.DTO.response.AlbumSimpleResponse;
import com.music.MusicCatalog.Entity.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
@Mapper(componentModel = "spring",uses = {SongMapper.class})
public interface AlbumMapper {

    
    Album toEntity(AlbumRequest request);
    
    @Mapping(target = "songs", source = "songs", qualifiedByName = "toSimpleResponse")
    AlbumResponse toResponse(Album album);

    @Named("toSimpleResponse")
    AlbumSimpleResponse toSimpleResponse(Album album);
} 