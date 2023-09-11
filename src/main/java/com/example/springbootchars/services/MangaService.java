package com.example.springbootchars.services;

import com.example.springbootchars.enums.MangaStatus;
import com.example.springbootchars.models.Manga;
import com.example.springbootchars.records.MangaRecordDto;
import com.example.springbootchars.repositories.MangaRepository;
import jakarta.transaction.Transactional;
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

    public Optional<Manga> addManga(MangaRecordDto mangaRecordDto) {
        Manga manga = new Manga(mangaRecordDto.name(), mangaRecordDto.releaseDate(), mangaRecordDto.synopsis(), MangaStatus.valueOf(mangaRecordDto.mangaStatus()));
        mangaRepository.save(manga);
        return Optional.of(manga);
    }

    public Optional<UUID> deleteManga(UUID mangaId) {
        if(mangaRepository.findById(mangaId).isEmpty()){
            return Optional.empty();
        }
        mangaRepository.deleteById(mangaId);
        return Optional.of(mangaId);
    }

    @Transactional
    public Optional<Manga> updateManga(UUID mangaId, MangaRecordDto mangaRecordDto) {
        Optional<Manga> manga = mangaRepository.findById(mangaId);
        if(manga.isEmpty()){
            return Optional.empty();
        }
        if(mangaRecordDto.name() != null){
            manga.get().setName(mangaRecordDto.name());
        }
        if(mangaRecordDto.releaseDate() != null){
            manga.get().setReleaseDate(mangaRecordDto.releaseDate());
        }
        if(mangaRecordDto.synopsis() != null){
            manga.get().setSynopsis(mangaRecordDto.synopsis());
        }
        if(mangaRecordDto.mangaStatus() != null){
            manga.get().setMangaStatus(MangaStatus.valueOf(mangaRecordDto.mangaStatus()));
        }
        return manga;
    }
}
