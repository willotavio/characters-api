package com.example.springbootchars;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");

    @Autowired
    public CharacterService(CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }

    public List<Character> getAllCharacters(){
        return characterRepository.findAll();
    }

    public Optional<Character> getCharacterById(UUID characterId) {
        Optional<Character> character = characterRepository.findById(characterId);
        return character;
    }

    public Character addCharacter(CharacterRecordDto characterRecordDto) {
        return characterRepository.save(new Character(characterRecordDto.name(), MonthDay.parse(characterRecordDto.dateOfBirth(), formatter)));
    }

    @Transactional
    public Optional<Character> updateCharacter(UUID characterId, CharacterRecordDto characterRecordDto) {
        Optional<Character> result = characterRepository.findById(characterId);
        if(result.isEmpty()){
            return result;
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
