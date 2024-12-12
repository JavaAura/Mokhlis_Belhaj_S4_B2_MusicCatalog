package com.music.MusicCatalog.Service;

import com.music.MusicCatalog.DTO.request.AlbumRequest;
import com.music.MusicCatalog.DTO.response.AlbumResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlbumService {
    
    AlbumResponse createAlbum(AlbumRequest request);
    Page<AlbumResponse> getAllAlbums(Pageable pageable);
}
