package com.music.MusicCatalog.Repository;

import com.music.MusicCatalog.Entity.Album;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends MongoRepository<Album, String> {
    
    Page<Album> findByTitleContaining(String title, Pageable pageable);
} 