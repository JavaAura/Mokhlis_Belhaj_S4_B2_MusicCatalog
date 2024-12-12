package com.music.MusicCatalog.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.MusicCatalog.DTO.request.AlbumRequest;
import com.music.MusicCatalog.DTO.response.AlbumResponse;
import com.music.MusicCatalog.Entity.Album;
import com.music.MusicCatalog.Mapper.AlbumMapper;
import com.music.MusicCatalog.Repository.AlbumRepository;
import com.music.MusicCatalog.Service.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public AlbumResponse createAlbum(AlbumRequest request) {
        Album album = albumMapper.toEntity(request);
        albumRepository.save(album);
        return albumMapper.toResponse(album);
    }
    
}
