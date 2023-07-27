package com.example.springbootchars.repositories;

import com.example.springbootchars.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CharacterRepository extends JpaRepository<Character, UUID> {
}
