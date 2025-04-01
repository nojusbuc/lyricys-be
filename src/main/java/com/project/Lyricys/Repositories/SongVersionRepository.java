package com.project.Lyricys.Repositories;

import com.project.Lyricys.Entities.SongVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongVersionRepository extends JpaRepository<SongVersion, Long> {
}
