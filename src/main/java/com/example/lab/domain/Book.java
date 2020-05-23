package com.example.lab.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titlu;
    private String autor;
    private String gen;
    private String link;

    public Book() {}

    public Book(String titlu, String autor, String gen) {
        this.titlu = titlu;
        this.autor = autor;
        this.gen = gen;
        this.link = "#";
    }

    public Book(String titlu, String autor, String gen, String link) {
        this.titlu = titlu;
        this.autor = autor;
        this.gen = gen;
        this.link = link;
    }

    public String getReadLink(){
        return link == null ? "#" : link ;
    }

    public Long getId() {
        return id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }
}
