package com.music.MusicCatalog.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.music.MusicCatalog.DTO.request.SongRequest;
import com.music.MusicCatalog.DTO.response.SongResponse;

public interface SongService {
    // admin fonctions
    SongResponse createSong(SongRequest request);
    SongResponse updateSong(String id, SongRequest request);
    boolean deleteSong(String id);

    // client fonctions

    Page<SongResponse> getAllSongs(Pageable pageable);
    Page<SongResponse> getAllSongsByTitle(String title, Pageable pageable);
  
}