package com.project.Lyricys.Controllers;

import com.project.Lyricys.DTOs.SongVersionDto;
import com.project.Lyricys.Entities.SongVersion;
import com.project.Lyricys.Services.SongVersionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/song-version")
public class SongVersionController {

    private final SongVersionService songVersionService;

    public SongVersionController (SongVersionService songVersionService) {this.songVersionService = songVersionService;}


    @PostMapping
    public ResponseEntity<SongVersion> createSongVersion(@RequestBody SongVersionDto songVersionDto) {
        SongVersion songVersion = songVersionService.createSongVersion(songVersionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(songVersion);
    }
}
