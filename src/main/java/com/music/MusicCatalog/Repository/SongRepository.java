package com.music.MusicCatalog.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

import com.music.MusicCatalog.Entity.Song;

public interface SongRepository extends MongoRepository<Song, String> {

    // get the songs by album id
    List<Song> findByAlbumId(String albumId);
}
