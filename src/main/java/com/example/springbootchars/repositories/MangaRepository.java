package com.example.springbootchars.repositories;

import com.example.springbootchars.models.Manga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MangaRepository extends JpaRepository<Manga, UUID> {
}
