package com.example.springbootchars;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class CharacterConfig {

    private CharacterRepository characterRepository;

    @Bean
    CommandLineRunner commandLineRunner(CharacterRepository characterRepository){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        return args -> {
            Character c1 = new Character(
                    "Sumi-Chan",
                        MonthDay.parse("03-20", formatter)
            );
            Character c2 = new Character(
                    "Hifumin-chan",
                    MonthDay.parse("01-23", formatter)
            );
            Character c3 = new Character(
                    "Umiko-chan",
                    MonthDay.parse("07-20", formatter)
            );
            characterRepository.saveAll(List.of(c1, c2, c3));
        };
    }

}
