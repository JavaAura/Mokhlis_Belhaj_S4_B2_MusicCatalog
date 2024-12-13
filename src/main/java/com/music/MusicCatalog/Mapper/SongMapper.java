package com.music.MusicCatalog.Mapper;

import com.music.MusicCatalog.DTO.request.SongRequest;
import com.music.MusicCatalog.DTO.response.SongResponse;
import com.music.MusicCatalog.DTO.response.SongSimpleResponse;
import com.music.MusicCatalog.Entity.Song;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SongMapper {
    
    @Mapping(target = "album.id", source = "albumId")
    Song toEntity(SongRequest request);
    
    @Mapping(target = "album", source = "album")
    SongResponse toResponse(Song song);

    @Named("toSimpleResponseList")
    List<SongSimpleResponse> toSimpleResponseList(List<Song> songs);


}