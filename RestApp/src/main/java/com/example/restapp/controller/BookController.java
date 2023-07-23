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

import java.util.*;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllBooks(){

        Map<String, Object> response = new LinkedHashMap<String, Object>();

        List<Book> bookList = bookService.getAllBooks();

        if(!bookList.isEmpty()){
            response.put("status", 1);
            response.put("data", bookList);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            response.put("status", 0);
            response.put("data", "Data is Not Found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id){


        Map<String, Object> response = new LinkedHashMap<String, Object>();

        Book book = bookService.getBookById(id);

        if(book == null){

            response.put("status", 0);
            response.put("data", "Data is Not Found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        }
        else{

            response.put("status", 1);
            response.put("data", book);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }

    }

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody Book book){


        Map<String, Object> response = new LinkedHashMap<String, Object>();

        bookService.addBook(book);

        response.put("status", 1);
        response.put("data", "Data saved successfully.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);




    }

    @PostMapping("/updateById/{id}")
    public ResponseEntity<?> updateBookById(@PathVariable Long id, @RequestBody Book updatedbook){

        Map<String, Object> response = new LinkedHashMap<String, Object>();

        Book book = bookService.getBookById(id);

        if(book == null){

            response.put("status", 0);
            response.put("data", "Data is Not Found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        }
        else{

            book.setTitle(updatedbook.getTitle());
            book.setAuthor(updatedbook.getAuthor());

            bookService.addBook(book);

            response.put("status", 1);
            response.put("data", "Data updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);

        }



    }

    @DeleteMapping("/deletedById/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id){


        Map<String, Object> response = new LinkedHashMap<String, Object>();

        Book book = bookService.getBookById(id);

        if(book == null){

            response.put("status", 0);
            response.put("data", "Data is Not Found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        }
        else{

            bookService.deleteBookById(id);

            response.put("status", 1);
            response.put("data", "Data updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);

        }




    }

}
