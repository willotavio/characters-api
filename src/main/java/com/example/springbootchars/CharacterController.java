package com.example.springbootchars;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "characters-api/character")
public class CharacterController {

    private final CharacterService characterService;
    @Autowired
    public CharacterController(CharacterService characterService){
        this.characterService = characterService;
    }

    @GetMapping
    public ResponseEntity<List<Character>> getCharacters(){
        return ResponseEntity.status(HttpStatus.OK).body(characterService.getAllCharacters());
    }

    @GetMapping(path = "{characterId}")
    public ResponseEntity<Object> getCharacterById(@PathVariable(value="characterId") UUID characterId){
        Optional<Character> character = characterService.getCharacterById(characterId);
        if(character.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Character not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(character);
    }

    @PostMapping
    public ResponseEntity<Character> addCharacter(@RequestBody @Valid CharacterRecordDto characterRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(characterService.addCharacter(characterRecordDto));
    }

    @PutMapping(path="{characterId}")
    public ResponseEntity<Object> updateCharacter(@PathVariable(value="characterId") UUID characterId,
                                                  @RequestBody CharacterRecordDto characterRecordDto){
        Optional<Character> result = characterService.updateCharacter(characterId, characterRecordDto);
        if(result.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Character not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Character with id " + characterId + " updated");
    }

    @DeleteMapping(path="{characterId}")
    public ResponseEntity<Object> deleteCharacter(@PathVariable(value="characterId") UUID characterId){
        Optional<Character> character = characterService.deleteCharacter(characterId);
        if(character.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Character not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Character with id " + characterId + " deleted");
    }
}
