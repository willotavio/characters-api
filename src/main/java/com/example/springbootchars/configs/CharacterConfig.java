package com.example.springbootchars.configs;

import com.example.springbootchars.enums.MangaStatus;
import com.example.springbootchars.models.Manga;
import com.example.springbootchars.models.Character;
import com.example.springbootchars.repositories.CharacterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class CharacterConfig {

    private CharacterRepository characterRepository;

    @Bean
    CommandLineRunner commandLineRunner(CharacterRepository characterRepository){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        Manga m1 = new Manga("Kanojo Okarishimasu", LocalDate.of(2017, 07, 12), "20-year-old college student Kazuya Kinoshita is feeling gloomy after his girlfriend dumped him for another guy. Tired of feeling alone, he decides to use the app \"Diamond\" to get himself a rental girlfriend.", MangaStatus.ON_GOING);

        Manga m2 = new Manga("New Game!", LocalDate.of(2013, 01, 28), "Having been inspired by the character designs of a particular video game when she was younger, Aoba Suzukaze, a high school graduate, begins working as a character designer for the game's developer, Eagle Jump.", MangaStatus.FINISHED);

        return args -> {
            Character c1 = new Character(
                    "Sumi-Chan",
                        MonthDay.parse("03-20", formatter),
                        m1
                        );
            Character c2 = new Character(
                    "Hifumin-chan",
                        MonthDay.parse("01-23", formatter),
                        m2
                        );
            Character c3 = new Character(
                    "Umiko-chan",
                        MonthDay.parse("07-20", formatter),
                        m2
                        );
            characterRepository.saveAll(List.of(c1, c2, c3));
        };
    }

}
