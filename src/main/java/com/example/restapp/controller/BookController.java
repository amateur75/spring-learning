package com.example.restapp.controller;


import com.example.restapp.model.Book;
import com.example.restapp.repository.BookRepository;
import com.example.restapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> bookList = new ArrayList<>();

        try {



            bookList = bookService.getAllBooks();

            if(bookList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(bookList, HttpStatus.OK);

    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){

        Book bookData = bookService.getBookById(id);


        return new ResponseEntity<>(bookData, HttpStatus.OK);

    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book){

        Book bookObject = bookService.addBook(book);

        return new ResponseEntity<>(bookObject, HttpStatus.OK);



    }

    @PostMapping("/updateById/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book updatedbook){

        Book book = bookService.updateBookById(id, updatedbook);

        if(book != null){


            return new ResponseEntity<>(book, HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);



    }

    @DeleteMapping("/deletedById/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id){


        if(bookService.deleteBookById(id))
        {
            return new ResponseEntity<>(HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

}
