package com.project.Lyricys.DTOs;

public record SongVersionDetailsDto(
        Long id,
        String content,
        String versionNotes,
        String versionName,
        Boolean isStarred,
        Boolean isArchived
) {}
