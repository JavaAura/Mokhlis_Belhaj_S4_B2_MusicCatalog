package com.music.MusicCatalog.Mapper;

import com.music.MusicCatalog.DTO.request.SongRequest;
import com.music.MusicCatalog.DTO.response.SongResponse;
import com.music.MusicCatalog.DTO.response.SongSimpleResponse;
import com.music.MusicCatalog.Entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SongMapper {
    

    Song toEntity(SongRequest request);
    
    @Mapping(target = "album", source = "album")
    SongResponse toResponse(Song song);


    @Named("toSimpleResponse")
    SongSimpleResponse toSimpleResponse(Song song);

}