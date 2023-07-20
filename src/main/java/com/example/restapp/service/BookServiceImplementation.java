package com.example.restapp.service;

import com.example.restapp.exception.NoBookFoundException;
import com.example.restapp.model.Book;
import com.example.restapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImplementation implements BookService{

    @Autowired
    private BookRepository bookRepository;


    @Override
    public Book getBookById(Long id) {

        Optional <Book> bookInfo = bookRepository.findById(id);

        Book book = null;

        if(bookInfo.isPresent()){
            book = bookInfo.get();
        }
        else {
            throw new NoBookFoundException("No Book has been found");
        }

        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().forEach(bookList::add);

        return bookList;
    }

    @Override
    public Book addBook(Book book) {

        bookRepository.save(book);

        return book;


    }


    @Override
    public void Update(Book book){

    }


    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
