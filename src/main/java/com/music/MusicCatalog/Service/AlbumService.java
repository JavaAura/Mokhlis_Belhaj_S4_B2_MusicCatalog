package com.music.MusicCatalog.Service;

import com.music.MusicCatalog.DTO.request.AlbumRequest;
import com.music.MusicCatalog.DTO.response.AlbumResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlbumService {
    // admin fonction
    AlbumResponse createAlbum(AlbumRequest request);
    AlbumResponse updateAlbum(String id, AlbumRequest request);

    // all users fonction
    Page<AlbumResponse> getAllAlbums(Pageable pageable);
    Page<AlbumResponse> getAlbumsByTitle(String title, Pageable pageable);
    Page<AlbumResponse> getAlbumsByArtist(String artist, Pageable pageable);
    Page<AlbumResponse> filterAlbumsByYear(int startYear, int endYear, Pageable pageable);
}
