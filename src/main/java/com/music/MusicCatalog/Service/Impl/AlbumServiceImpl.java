package com.music.MusicCatalog.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.music.MusicCatalog.DTO.request.AlbumRequest;
import com.music.MusicCatalog.DTO.response.AlbumResponse;
import com.music.MusicCatalog.Entity.Album;
import com.music.MusicCatalog.Exception.ResponseException;
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

    @Override
    public Page<AlbumResponse> getAllAlbums(Pageable pageable) {
        Page<Album> albums = albumRepository.findAll(pageable);
        return albums.map(albumMapper::toResponse);
    }

    @Override
    public Page<AlbumResponse> getAlbumsByTitle(String title, Pageable pageable) {
        Page<Album> albums = albumRepository.findByTitleContaining(title, pageable);
        if (albums.isEmpty()) {
            throw new ResponseException("Aucun album trouvé avec ce titre " + title, HttpStatus.NOT_FOUND);
        }
        return albums.map(albumMapper::toResponse);
    }
}
