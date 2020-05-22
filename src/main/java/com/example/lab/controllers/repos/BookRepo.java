package com.example.lab.controllers.repos;

import com.example.lab.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepo extends CrudRepository<Book, Long> {
    List<Book> findByAutor(String autor);
    List<Book> findByTitlu(String titlu);
    List<Book> findByTitluContainingAndAutorContainingAndGenContaining(String titlu, String autor, String gen);

}
