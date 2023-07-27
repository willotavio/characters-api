package com.example.springbootchars.controllers;

import com.example.springbootchars.models.Manga;
import com.example.springbootchars.services.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "characters-api/manga")
public class MangaController {

    private final MangaService mangaService;

    @Autowired
    public MangaController(MangaService mangaService) {
        this.mangaService = mangaService;
    }

    @GetMapping
    public ResponseEntity<List<Manga>> getAllAnimes(){
        return ResponseEntity.status(HttpStatus.OK).body(mangaService.getAllMangas());
    }

}
