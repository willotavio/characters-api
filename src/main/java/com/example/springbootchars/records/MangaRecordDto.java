package com.example.springbootchars.records;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record MangaRecordDto(@NotBlank String name,
                             @NotBlank LocalDate releaseDate,
                             @NotBlank String synopsis) {
}
