package com.project.Lyricys.Services;

import com.project.Lyricys.Entities.Song;
import com.project.Lyricys.Entities.SongVersion;
import com.project.Lyricys.Repositories.SongRepository;
import com.project.Lyricys.Repositories.SongVersionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SongService {
    private final SongRepository songRepository;
    private final SongVersionRepository songVersionRepository;

    public SongService(SongRepository songRepository, SongVersionRepository songVersionRepository) {
        this.songRepository = songRepository;
        this.songVersionRepository = songVersionRepository;
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song getSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Song not found by id " + id));
    }

    @Transactional
    public Song CreateSong(String title) {
        Song song = new Song();
        song.setTitle(title);

        Song savedSong = songRepository.save(song);

        SongVersion firstVersion = new SongVersion();
        firstVersion.setSong(savedSong);
        firstVersion.setContent("");
        SongVersion savedVersion = songVersionRepository.save(firstVersion);

        savedSong.setCurrentVersionId(savedVersion.getId());
        return songRepository.save(savedSong);
    }

}
