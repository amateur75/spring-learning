package com.example.restapp.service;

import com.example.restapp.model.Book;

import java.util.List;

public interface BookService {

    Book getBookById(Long id);

    List<Book> getAllBooks();

    Book addBook(Book book);

    Book updateBookById(Long id, Book updatedBook);

    boolean deleteBookById(Long id);

}
