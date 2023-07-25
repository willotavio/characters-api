package com.example.springbootchars;

import jakarta.validation.constraints.NotBlank;

public record CharacterRecordDto(@NotBlank String name, @NotBlank String dateOfBirth) {
}