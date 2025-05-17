package com.project.Lyricys.Services;

import com.project.Lyricys.DTOs.SongVersionCreateDto;
import com.project.Lyricys.DTOs.SongVersionRequestDto;
import com.project.Lyricys.DTOs.SongVersionResponseDto;
import com.project.Lyricys.Entities.Song;
import com.project.Lyricys.Entities.SongVersion;
import com.project.Lyricys.Repositories.SongRepository;
import com.project.Lyricys.Repositories.SongVersionRepository;
import com.project.Lyricys.Security.CustomUserDetails;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SongVersionService {

    private final SongVersionRepository songVersionRepository;
    private final SongRepository songRepository;

    public SongVersionService(SongVersionRepository songVersionRepository, SongRepository songRepository) {
        this.songVersionRepository = songVersionRepository;
        this.songRepository = songRepository;

    }


    @Transactional
    public SongVersionResponseDto createSongVersion(Long songId, SongVersionCreateDto songVersionCreateDto) {

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new EntityNotFoundException("Song not found with id: " + songId));

        SongVersion newSongVersion = new SongVersion();
        newSongVersion.setSong(song);
        newSongVersion.setContent(songVersionCreateDto.getContent());
        newSongVersion.setVersionName(songVersionCreateDto.getName());

        SongVersion savedSongVersion = songVersionRepository.save(newSongVersion);

        SongVersionResponseDto songVersionResponseDto = SongVersionResponseDto.fromEntity(savedSongVersion);

        song.setCurrentVersion(savedSongVersion);
        song.getVersions().add(savedSongVersion);
        songRepository.save(song);

        return songVersionResponseDto;
    }

    @Transactional
    public SongVersionResponseDto updateSongVersion(Long songId, SongVersionRequestDto songVersionRequestDto, CustomUserDetails principal) {

        SongVersion songVersion = songVersionRepository.findByIdAndSongId(songVersionRequestDto.id(), songId)
                .orElseThrow(() -> new EntityNotFoundException("Song version not found with id: " + songVersionRequestDto.id()));

        Song song = songRepository.findById(songId).orElseThrow(() -> new EntityNotFoundException("Song not found with id: " + songId));

        if (!song.getOwner().getId().equals(principal.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "This song does not exist or belongs to a different user."

            );
        }

        if (songVersionRequestDto.versionName() != null) songVersion.setVersionName(songVersionRequestDto.versionName());
        if (songVersionRequestDto.versionNotes() != null) songVersion.setVersionNotes(songVersionRequestDto.versionNotes());
        if (songVersionRequestDto.content() != null) songVersion.setContent(songVersionRequestDto.content());
        songVersion.setStarred(songVersionRequestDto.starred());
        songVersion.setArchived(songVersionRequestDto.archived());



        songVersionRepository.save(songVersion);

        return SongVersionResponseDto.fromEntity(songVersion);



    }


}
