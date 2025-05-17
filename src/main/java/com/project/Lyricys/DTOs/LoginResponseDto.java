package com.project.Lyricys.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

public record LoginResponseDto(
        String token,
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        Long ownerId
) { }
