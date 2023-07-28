package com.example.springbootchars.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table
public class Manga implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private LocalDate releaseDate;
    private String synopsis;
    @OneToMany(mappedBy = "manga", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Character> characters;

    public Manga(){
    }
    public Manga(UUID id, String name, LocalDate releaseDate, String synopsis) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
    }
    public Manga(String name, LocalDate releaseDate, String synopsis) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manga manga = (Manga) o;
        return Objects.equals(id, manga.id) && Objects.equals(name, manga.name) && Objects.equals(releaseDate, manga.releaseDate) && Objects.equals(synopsis, manga.synopsis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseDate, synopsis);
    }

    @Override
    public String toString() {
        return "Manga{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }
}
