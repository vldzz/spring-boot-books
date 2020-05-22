package com.example.lab.controllers;

import com.example.lab.controllers.repos.BookRepo;
import com.example.lab.domain.Book;
import com.example.lab.google.api.OnlineBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    BookRepo bookRepo;

    @RequestMapping("/")
    public String main(){
        return "main";
    }

    @RequestMapping("/library")
    public String library(Map<String, Object> model){
        Iterable<Book> booksFromDb = bookRepo.findAll();

        model.put("books", booksFromDb);
        return "library";
    }

    @PostMapping("add")
    public String add(@RequestParam String title, @RequestParam String author,
                      @RequestParam String genre, Map<String, Object> model){

        Book book = new Book(title, author, genre);

        if(book.getTitlu() != "" && book.getAutor() != "" && book.getGen() != "")
            bookRepo.save(book);

        Iterable<Book> booksFromDb = bookRepo.findAll();
        model.put("books", booksFromDb);

        return "library";
    }

    @RequestMapping("/search")
    public String search(Map<String, Object> model){
        Iterable<Book> books = bookRepo.findAll();

        model.put("books", books);

        return "search";
    }

    @PostMapping("searchTitle")
    public String searchBy(@RequestParam(required = false, defaultValue = "") String titlu,
                           @RequestParam(required = false, defaultValue = "") String author,
                           @RequestParam(required = false, defaultValue = "") String genre,
                           Map<String, Object> model){

        List<Book> books = bookRepo.findByTitluContainingAndAutorContainingAndGenContaining(titlu, author, genre);

        model.put("books", books);

        return "search";
    }

    @RequestMapping("/googlebooks")
    public String googleBooks(Map<String, Object> model){
        return "googleBooks";
    }

    @PostMapping("onlinebooks")
    public String findGoogleBooks(@RequestParam(required = true) String keyword, Map<String, Object> model){

        OnlineBooks ob = new OnlineBooks(keyword);
        List<Book> onlineBooks =  ob.getOnlineBooks();

        model.put("books", onlineBooks);

        return "googleBooks";
    }
}
