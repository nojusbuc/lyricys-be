package com.project.Lyricys.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.Lyricys.Entities.SongVersion;

import java.time.LocalDateTime;

public record SongVersionResponseDto(
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        Long id,
        String versionName,
        String content,
        String versionNotes,
        boolean starred,
        boolean archived,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) {
    public static SongVersionResponseDto fromEntity(SongVersion songVersion) {
        if (songVersion == null) {
            return null;
        }

        return new SongVersionResponseDto(
                songVersion.getId(),
                songVersion.getVersionName(),
                songVersion.getContent(),
                songVersion.getVersionNotes(),
                songVersion.isStarred(),
                songVersion.isArchived(),
                songVersion.getCreatedDate(),
                songVersion.getModifiedDate()
        );
    }
}