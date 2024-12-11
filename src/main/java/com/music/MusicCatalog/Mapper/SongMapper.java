package com.music.MusicCatalog.Mapper;

import com.music.MusicCatalog.DTO.request.SongRequest;
import com.music.MusicCatalog.DTO.response.SongResponse;
import com.music.MusicCatalog.DTO.response.SongSimpleResponse;
import com.music.MusicCatalog.Entity.Song;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {
    

    Song toEntity(SongRequest request);
    
    
    SongResponse toResponse(Song song);
    
    SongSimpleResponse toSimpleResponse(Song song);
}