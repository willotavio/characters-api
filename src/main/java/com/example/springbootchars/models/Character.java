package com.example.springbootchars.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.time.MonthDay;
import java.util.Objects;
import java.util.UUID;

import static org.hibernate.annotations.CascadeType.PERSIST;

@Entity
@Table
public class Character implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private MonthDay dateOfBirth;
    @ManyToOne
    @Cascade(PERSIST)
    @JoinColumn(name = "mangaId", referencedColumnName = "id")
    private Manga manga;

    public Character(){

    }

    public Character(String name, MonthDay dateOfBirth, Manga manga) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.manga = manga;
    }

    public Character(UUID id, String name, MonthDay dateOfBirth, Manga manga) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.manga = manga;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MonthDay getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(MonthDay dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Manga getAnime() {
        return manga;
    }

    public void setAnime(Manga manga) {
        this.manga = manga;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(id, character.id) && Objects.equals(name, character.name) && Objects.equals(dateOfBirth, character.dateOfBirth) && Objects.equals(manga, character.manga);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, manga);
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", manga=" + manga +
                '}';
    }
}
