package com.music.MusicCatalog.Service;

import com.music.MusicCatalog.DTO.request.SongRequest;
import com.music.MusicCatalog.DTO.response.SongResponse;

public interface SongService {
    // admin fonctions
    SongResponse createSong(SongRequest request);

    // client fonctions
  
}