package com.example.springbootchars.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CharacterRecordDto(@NotBlank String name, @NotBlank String dateOfBirth, @NotNull UUID mangaId) {
}