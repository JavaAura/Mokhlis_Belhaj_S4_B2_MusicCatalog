package com.music.MusicCatalog.Repository;

import com.music.MusicCatalog.Entity.Album;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends MongoRepository<Album, String> {

    Optional<Album> findById(String id);
    
    Page<Album> findByTitleContaining(String title, Pageable pageable);
    Page<Album> findByArtistContaining(String artist, Pageable pageable);
    Page<Album> findByReleaseYearBetween(int startYear, int endYear, Pageable pageable);

 

   
} 