package com.music.MusicCatalog.Service;

import com.music.MusicCatalog.DTO.request.AlbumRequest;
import com.music.MusicCatalog.DTO.response.AlbumResponse;
import com.music.MusicCatalog.Entity.Album;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AlbumService {
    // admin fonction
    AlbumResponse createAlbum(AlbumRequest request);
    AlbumResponse updateAlbum(String id, AlbumRequest request);
    void deleteAlbum(String id);

    // all users fonction
    Page<AlbumResponse> getAllAlbums(Pageable pageable);
    Page<AlbumResponse> getAlbumsByTitle(String title, Pageable pageable);
    Page<AlbumResponse> getAlbumsByArtist(String artist, Pageable pageable);
    Page<AlbumResponse> filterAlbumsByYear(int startYear, int endYear, Pageable pageable);

    // need it for song creation
    Album getAlbumById(String id);
    Album updateAlbum(Album album);
}
