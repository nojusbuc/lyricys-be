package com.project.Lyricys.Services;

import com.project.Lyricys.DTOs.SongDetailsDto;
import com.project.Lyricys.Entities.Song;
import com.project.Lyricys.Entities.SongVersion;
import com.project.Lyricys.Entities.User;
import com.project.Lyricys.Repositories.SongRepository;
import com.project.Lyricys.Repositories.SongVersionRepository;
import com.project.Lyricys.Repositories.UserRepository;
import com.project.Lyricys.Security.CustomUserDetails;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SongService {
    private final SongRepository songRepository;
    private final SongVersionRepository songVersionRepository;
    private final UserRepository userRepository;

    public SongService(SongRepository songRepository, SongVersionRepository songVersionRepository, UserRepository userRepository) {
        this.songRepository = songRepository;
        this.songVersionRepository = songVersionRepository;
        this.userRepository = userRepository;
    }

    public List<SongDetailsDto> getAllSongs() {
        List<Song> songs = songRepository.findAll();


        return songs.stream().map(SongDetailsDto::fromEntity).toList();
    }

    public Song getSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Song not found by id " + id));
    }

    @Transactional
    public SongDetailsDto CreateSong(String title, CustomUserDetails principal) {

        User user = userRepository.findById(principal.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + principal.getId()));


        Song song = new Song();
        song.setTitle(title);
        song.setOwner(user);

        Song savedSong = songRepository.save(song);

        SongVersion firstVersion = new SongVersion();
        firstVersion.setSong(savedSong);
        firstVersion.setContent("");
        firstVersion.setVersionName("Created song.");
        SongVersion savedVersion = songVersionRepository.save(firstVersion);

        savedSong.setCurrentVersion(savedVersion);

        savedSong.getVersions().add(savedVersion);

        Song finalSong = songRepository.save(savedSong);

        return SongDetailsDto.fromEntity(finalSong);
    }

}
