package com.example.springbootchars.services;

import com.example.springbootchars.models.Manga;
import com.example.springbootchars.records.CharacterRecordDto;
import com.example.springbootchars.models.Character;
import com.example.springbootchars.repositories.CharacterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final MangaService mangaService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");

    @Autowired
    public CharacterService(CharacterRepository characterRepository, MangaService mangaService){
        this.characterRepository = characterRepository;
        this.mangaService = mangaService;
    }

    public List<Character> getAllCharacters(){
        return characterRepository.findAll();
    }

    public Optional<Character> getCharacterById(UUID characterId) {
        Optional<Character> character = characterRepository.findById(characterId);
        return character;
    }

    public Optional<Character> addCharacter(CharacterRecordDto characterRecordDto) {
        Optional<Manga> manga = mangaService.getMangaById(characterRecordDto.mangaId());
        if(manga.isEmpty()){
            return Optional.empty();
        }
        Manga mangaO = manga.get();
        Character character = new Character(characterRecordDto.name(), MonthDay.parse(characterRecordDto.dateOfBirth(), formatter), mangaO);
        characterRepository.save(character);
        return Optional.of(character);
    }

    @Transactional
    public Optional<Character> updateCharacter(UUID characterId, CharacterRecordDto characterRecordDto) {
        Optional<Character> result = characterRepository.findById(characterId);
        if(result.isEmpty()){
            return Optional.empty();
        }
        if(characterRecordDto.mangaId() != null){
            Optional<Manga> manga = mangaService.getMangaById(characterRecordDto.mangaId());
            if(manga.isEmpty()){
                return Optional.empty();
            }
            Manga mangaO = manga.get();
            result.get().setManga(mangaO);
        }
        if(characterRecordDto.name() != null){
            result.get().setName(characterRecordDto.name());
        }
        if(characterRecordDto.dateOfBirth() != null){
            result.get().setDateOfBirth(MonthDay.parse(characterRecordDto.dateOfBirth(), formatter));
        }
        return result;
    }

    public Optional<Character> deleteCharacter(UUID characterId) {
        Optional<Character> character = characterRepository.findById(characterId);
        if(character.isEmpty()){
            return character;
        }
        characterRepository.deleteById(characterId);
        return character;
    }
}
