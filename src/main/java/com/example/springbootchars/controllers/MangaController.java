package com.example.springbootchars.controllers;

import com.example.springbootchars.models.Manga;
import com.example.springbootchars.records.MangaRecordDto;
import com.example.springbootchars.services.MangaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "characters-api/manga")
public class MangaController {

    private final MangaService mangaService;

    @Autowired
    public MangaController(MangaService mangaService) {
        this.mangaService = mangaService;
    }

    @GetMapping
    public ResponseEntity<List<Manga>> getAllMangas(){
        return ResponseEntity.status(HttpStatus.OK).body(mangaService.getAllMangas());
    }

    @PostMapping
    public ResponseEntity<Optional<Manga>> addManga(@RequestBody @Valid MangaRecordDto mangaRecordDto){
        Optional<Manga> manga = mangaService.addManga(mangaRecordDto);
        if(manga.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Optional.empty());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(manga);
    }

}
