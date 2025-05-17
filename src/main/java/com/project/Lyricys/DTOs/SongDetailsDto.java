package com.project.Lyricys.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.Lyricys.Entities.Song;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public record SongDetailsDto(
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        Long id,
        String title,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate,
        boolean starred,
        boolean archived,
        SongVersionRequestDto currentVersion,
        List<SongVersionRequestDto> versions,
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        Long ownerId
) {
    public static SongDetailsDto fromEntity(Song song) {
        if (song == null) {
            return null;
        }

        List<SongVersionRequestDto> versionDtos = song.getVersions() != null ? song.getVersions()
                .stream()
                .map(SongVersionRequestDto::fromEntity)
                .toList() : Collections.emptyList();

        return new SongDetailsDto(
                song.getId(),
                song.getTitle(),
                song.getCreatedDate(),
                song.getModifiedDate(),
                song.isStarred(),
                song.isArchived(),
                SongVersionRequestDto.fromEntity(song.getCurrentVersion()),
                versionDtos,
                song.getOwner().getId()
        );
    }
}
