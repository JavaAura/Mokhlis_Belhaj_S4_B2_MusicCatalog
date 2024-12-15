package com.music.MusicCatalog.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.music.MusicCatalog.Entity.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(String name);
    Optional<Role> findById(String id);
}
