package com.project.Lyricys.DTOs;

import lombok.Data;

@Data
public class SongVersionUpdateDto {
    private String content;
    private String versionNotes;
    private String versionName;
    private Boolean isStarred;
    private Boolean isArchived;
}
