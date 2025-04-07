package com.project.Lyricys.Services;

import com.project.Lyricys.DTOs.SongVersionDto;
import com.project.Lyricys.DTOs.SongVersionUpdateDto;
import com.project.Lyricys.Entities.Song;
import com.project.Lyricys.Entities.SongVersion;
import com.project.Lyricys.Repositories.SongRepository;
import com.project.Lyricys.Repositories.SongVersionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;

@Service
public class SongVersionService {

    private final SongVersionRepository songVersionRepository;
    private final SongRepository songRepository;

    public SongVersionService(SongVersionRepository songVersionRepository, SongRepository songRepository) {
        this.songVersionRepository = songVersionRepository;
        this.songRepository = songRepository;

    }


    @Transactional
    public SongVersion createSongVersion(Long songId, SongVersionDto songVersionDto) {

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new EntityNotFoundException("Song not found with id: " + songId));

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

    @Transactional
    public SongVersion updateSongVersion(Long songId, long songVersionid, SongVersionUpdateDto songVersionUpdateDto) {
        SongVersion songVersion = songVersionRepository.findById(songVersionid)
                .orElseThrow(() -> new EntityNotFoundException("Song version not found with ID: " + songVersionid));
        if (songVersionUpdateDto.getVersionName() != null) songVersion.setVersionName(songVersionUpdateDto.getVersionName());
        if (songVersionUpdateDto.getIsArchived() != null) songVersion.setArchived(songVersionUpdateDto.getIsArchived());
        if (songVersionUpdateDto.getVersionNotes() != null) songVersion.setVersionNotes(songVersionUpdateDto.getVersionNotes());
        if (songVersionUpdateDto.getContent() != null) songVersion.setContent(songVersionUpdateDto.getContent());
        if (songVersionUpdateDto.getIsStarred() != null) songVersion.setStarred(songVersionUpdateDto.getIsStarred());

        return songVersionRepository.save(songVersion);
    }


}
