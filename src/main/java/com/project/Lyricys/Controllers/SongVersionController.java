package com.project.Lyricys.Controllers;

import com.project.Lyricys.DTOs.SongVersionDto;
import com.project.Lyricys.DTOs.SongVersionUpdateDto;
import com.project.Lyricys.Entities.SongVersion;
import com.project.Lyricys.Services.SongVersionService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/songs/")
public class SongVersionController {

    private final SongVersionService songVersionService;

    public SongVersionController (SongVersionService songVersionService) {this.songVersionService = songVersionService;}


    @PostMapping("{songId}/versions/")
    public ResponseEntity<SongVersion> createSongVersion(@PathVariable Long songId, @RequestBody SongVersionDto songVersionDto) {
        SongVersion songVersion = songVersionService.createSongVersion(songId, songVersionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(songVersion);
    }

    @PatchMapping("/{songId}/versions/{songVersionId}")
    public ResponseEntity<SongVersion> updateSongVersion(@PathVariable Long songId,
                                                         @PathVariable Long songVersionId,
                                                         @RequestBody SongVersionUpdateDto songVersionUpdateDto) {
        SongVersion updatedSongVersion = songVersionService.updateSongVersion(songId, songVersionId, songVersionUpdateDto);
        return ResponseEntity.ok(updatedSongVersion);
    }
}
