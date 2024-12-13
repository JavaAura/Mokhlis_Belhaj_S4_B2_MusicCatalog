package com.music.MusicCatalog.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

import com.music.MusicCatalog.Entity.Song;

public interface SongRepository extends MongoRepository<Song, String> {

    // get the songs by album id
    List<Song> findByAlbumId(String albumId);

    // for the client
    Page<Song> findByTitleContaining(String title, Pageable pageable);
    Page<Song> findByAlbumId(String albumId, Pageable pageable);
    
}
