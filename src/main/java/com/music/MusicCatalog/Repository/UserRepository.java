package com.music.MusicCatalog.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.music.MusicCatalog.Entity.Users;

public interface UserRepository extends MongoRepository<Users, String> {
    
    Optional<Users> findByUsername(String username);
}
