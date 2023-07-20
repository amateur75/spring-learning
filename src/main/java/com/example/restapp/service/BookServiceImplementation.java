package com.example.restapp.service;

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
        return bookRepository.findById(id).orElseThrow();
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
    public Book updateBookById(Long id, Book updateBook) {
        Optional<Book> oldBook = bookRepository.findById(id);

        if(oldBook.isPresent()){
            Book updatedBookData = oldBook.get();
            updatedBookData.setAuthor(updateBook.getAuthor());
            updatedBookData.setTitle(updateBook.getTitle());

            Book bookObject = bookRepository.save(updatedBookData);

            return bookObject;

        }

        return null;
    }

    @Override
    public boolean deleteBookById(Long id) {

        Optional<Book> book = bookRepository.findById(id);

        if(book.isPresent()){
            bookRepository.deleteById(id);
            return true;
        }

        return false;

    }
}
