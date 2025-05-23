package com.project.Lyricys.DTOs;

public record SongVersionUpdateDto(
        Long id,
        String content,
        String versionNotes,
        String versionName,
        Boolean isStarred,
        Boolean isArchived
) {}
