package com.music.MusicCatalog.Service.Impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.music.MusicCatalog.DTO.request.SongRequest;
import com.music.MusicCatalog.DTO.response.SongResponse;
import com.music.MusicCatalog.Entity.Album;
import com.music.MusicCatalog.Entity.Song;
import com.music.MusicCatalog.Exception.ResponseException;
import com.music.MusicCatalog.Mapper.SongMapper;
import com.music.MusicCatalog.Repository.SongRepository;
import com.music.MusicCatalog.Service.AlbumService;
import com.music.MusicCatalog.Service.SongService;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
   

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongMapper songMapper;

    @Override
    public SongResponse createSong(SongRequest request) {
        Song song = songMapper.toEntity(request);
        Album album = albumService.getAlbumById(request.getAlbumId());
        
        song.setAlbum(album);
        List<Song> songs = album.getSongs();
        
        if (songs != null && songs.stream().anyMatch(s -> s.getTrackNumber() == request.getTrackNumber())) {
            throw new ResponseException("Le numéro de piste est déjà utilisé", HttpStatus.BAD_REQUEST);
        }
        Song savedSong = songRepository.save(song);
        songs.add(savedSong);
        album.setSongs(songs);
        albumService.updateAlbum(album);
        return songMapper.toResponse(savedSong);
    }

    @Override
    public SongResponse updateSong(String id, SongRequest request) {
        Song song = songRepository.findById(id).orElseThrow(() -> new ResponseException("Chanson non trouvée", HttpStatus.NOT_FOUND));
        if (request.getTitle() == null && request.getDuration() == null && request.getTrackNumber() == null) {
            throw new ResponseException("Aucune information fournie", HttpStatus.BAD_REQUEST);
        }
        if (request.getTitle() != null) {
            song.setTitle(request.getTitle());
        }
        if (request.getDuration() != null) {
            song.setDuration(request.getDuration());
        }
        if (request.getTrackNumber() != null) {
            if(song.getTrackNumber()!=request.getTrackNumber()){
                Album album = albumService.getAlbumById(song.getAlbum().getId());
                List<Song> songs = album.getSongs();

                if(songs!=null && songs.stream().anyMatch(s -> s.getTrackNumber() == request.getTrackNumber())){
                    throw new ResponseException("Le numéro de piste est déjà utilisé", HttpStatus.BAD_REQUEST);
                }
            }
            song.setTrackNumber(request.getTrackNumber());
        }
        Song updatedSong = songRepository.save(song);
        return songMapper.toResponse(updatedSong);
    }

    @Override
    public boolean deleteSong(String id) {
        Song song = songRepository.findById(id).orElseThrow(() -> new ResponseException("Chanson non trouvée", HttpStatus.NOT_FOUND));
        songRepository.delete(song);
        Album album = albumService.getAlbumById(song.getAlbum().getId());
        List<Song> songs = album.getSongs();
        songs.remove(song);
        album.setSongs(songs);
        albumService.updateAlbum(album);
        return true;
    }


    // client fonctions
    @Override
    public Page<SongResponse> getAllSongs(Pageable pageable) {
        Page<Song> songs = songRepository.findAll(pageable);
        return songs.map(songMapper::toResponse);
    }

    @Override
    public Page<SongResponse> getAllSongsByTitle(String title, Pageable pageable) {
        Page<Song> songs = songRepository.findByTitleContaining(title, pageable);
        if(songs.isEmpty()){
            throw new ResponseException("Aucune chanson trouvée", HttpStatus.NOT_FOUND);
        }
        return songs.map(songMapper::toResponse);
    }


    @Override
    public Page<SongResponse> getAllSongsByAlbum(String album, Pageable pageable) {
        Page<Song> songs = songRepository.findByAlbumId(album, pageable);
        return songs.map(songMapper::toResponse);
    }
   
}