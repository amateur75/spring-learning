package com.example.restapp.controller;


import com.example.restapp.model.Book;
import com.example.restapp.repository.BookRepository;
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
    private BookRepository bookRepository;


    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> bookList = new ArrayList<>();
        try {



            bookRepository.findAll().forEach(bookList::add);

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

        Optional<Book> bookData = bookRepository.findById(id);

        if(bookData.isPresent()){
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book){

        Book bookObject = bookRepository.save(book);

        return new ResponseEntity<>(bookObject, HttpStatus.OK);



    }

    @PostMapping("/updateById/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book updatedbook){

        Optional<Book> oldBook = bookRepository.findById(id);

        if(oldBook.isPresent()){
            Book updatedBookData = oldBook.get();
            updatedBookData.setAuthor(updatedbook.getAuthor());
            updatedBookData.setTitle(updatedbook.getTitle());

            Book bookObject = bookRepository.save(updatedBookData);

            return new ResponseEntity<>(bookObject, HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);



    }

    @DeleteMapping("/deletedById/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id){


        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }


    }

}
