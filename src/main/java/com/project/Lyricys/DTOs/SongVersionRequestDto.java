package com.project.Lyricys.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.Lyricys.Entities.SongVersion;

import java.time.LocalDateTime;

public record SongVersionRequestDto(
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        Long id,
        String versionName,
        String content,
        String versionNotes,
        boolean starred,
        boolean archived
) {
    public static SongVersionRequestDto fromEntity(SongVersion songVersion) {
        if (songVersion == null) {
            return null;
        }

        return new SongVersionRequestDto(
                songVersion.getId(),
                songVersion.getVersionName(),
                songVersion.getContent(),
                songVersion.getVersionNotes(),
                songVersion.isStarred(),
                songVersion.isArchived()
        );
    }
}