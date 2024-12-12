package com.music.MusicCatalog.Service;

import com.music.MusicCatalog.DTO.request.AlbumRequest;
import com.music.MusicCatalog.DTO.response.AlbumResponse;

public interface AlbumService {
    
    AlbumResponse createAlbum(AlbumRequest request);
}
