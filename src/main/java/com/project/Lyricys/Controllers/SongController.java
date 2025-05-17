package com.project.Lyricys.Controllers;

import com.project.Lyricys.DTOs.SongCreationDto;
import com.project.Lyricys.DTOs.SongDetailsDto;
import com.project.Lyricys.Entities.Song;
import com.project.Lyricys.Entities.SongVersion;
import com.project.Lyricys.Repositories.SongRepository;
import com.project.Lyricys.Repositories.SongVersionRepository;
import com.project.Lyricys.Security.CustomUserDetails;
import com.project.Lyricys.Services.SongService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public ResponseEntity<List<SongDetailsDto>> getAllSongs() {
        return ResponseEntity.ok(songService.getAllSongs());
    }

    @PostMapping
    public ResponseEntity<SongDetailsDto> createSong(@RequestBody SongCreationDto songDto, @AuthenticationPrincipal CustomUserDetails principal) {



        SongDetailsDto savedSong = songService.CreateSong(songDto.getTitle(), principal);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSong);
    }

}
