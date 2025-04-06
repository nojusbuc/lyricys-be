package com.project.Lyricys.Services;

import com.project.Lyricys.DTOs.SongVersionDto;
import com.project.Lyricys.Entities.Song;
import com.project.Lyricys.Entities.SongVersion;
import com.project.Lyricys.Repositories.SongRepository;
import com.project.Lyricys.Repositories.SongVersionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SongVersionService {

    private final SongVersionRepository songVersionRepository;
    private final SongRepository songRepository;

    public SongVersionService(SongVersionRepository songVersionRepository, SongRepository songRepository) {
        this.songVersionRepository = songVersionRepository;
        this.songRepository = songRepository;

    }


    @Transactional
    public SongVersion createSongVersion(SongVersionDto songVersionDto) {

        Song song = songRepository.findById(songVersionDto.getSongId())
                .orElseThrow(() -> new EntityNotFoundException("Song not found with id: " + songVersionDto.getSongId()));

        SongVersion newSongVersion = new SongVersion();
        newSongVersion.setSong(song);
        newSongVersion.setContent(songVersionDto.getContent());
        newSongVersion.setVersionName(songVersionDto.getName());

        SongVersion savedSongVersion = songVersionRepository.save(newSongVersion);

        song.setCurrentVersion(savedSongVersion);
        song.getVersions().add(savedSongVersion);
        songRepository.save(song);

        return savedSongVersion;
    }

}
