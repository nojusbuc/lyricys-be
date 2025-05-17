package com.project.Lyricys.Controllers;

import com.project.Lyricys.DTOs.SongVersionCreateDto;
import com.project.Lyricys.DTOs.SongVersionRequestDto;
import com.project.Lyricys.DTOs.SongVersionResponseDto;
import com.project.Lyricys.Security.CustomUserDetails;
import com.project.Lyricys.Services.SongVersionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/songs/")
public class SongVersionController {

    private final SongVersionService songVersionService;

    public SongVersionController (SongVersionService songVersionService) {this.songVersionService = songVersionService;}


    @PostMapping("{songId}/versions/")
    public ResponseEntity<SongVersionResponseDto> createSongVersion(@PathVariable Long songId, @RequestBody SongVersionCreateDto songVersionCreateDto) {
        SongVersionResponseDto songVersion = songVersionService.createSongVersion(songId, songVersionCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(songVersion);
    }

    @PatchMapping("/{songId}/versions/{songVersionId}")
    public ResponseEntity<SongVersionResponseDto> updateSongVersion(@PathVariable Long songId,
                                                                   @RequestBody SongVersionRequestDto songVersionRequestDto,
                                                                   @AuthenticationPrincipal CustomUserDetails principal) {
        SongVersionResponseDto updatedSongVersion = songVersionService.updateSongVersion(songId, songVersionRequestDto, principal);
        return ResponseEntity.ok(updatedSongVersion);
    }
}
