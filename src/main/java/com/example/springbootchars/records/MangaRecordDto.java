package com.example.springbootchars.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MangaRecordDto(@NotBlank String name,
                             @NotNull LocalDate releaseDate,
                             @NotBlank String synopsis,
                             @NotNull Integer mangaStatus) {
}
