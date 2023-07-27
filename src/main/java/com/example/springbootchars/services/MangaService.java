package com.example.springbootchars.services;

import com.example.springbootchars.models.Manga;
import com.example.springbootchars.repositories.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MangaService {

    private final MangaRepository mangaRepository;

    @Autowired
    public MangaService(MangaRepository mangaRepository) {
        this.mangaRepository = mangaRepository;
    }

    public List<Manga> getAllMangas(){
        return mangaRepository.findAll();
    }

    public Optional<Manga> getMangaById(UUID id){
        return mangaRepository.findById(id);
    }

}
