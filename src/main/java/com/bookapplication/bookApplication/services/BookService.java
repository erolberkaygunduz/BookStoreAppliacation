package com.bookapplication.bookApplication.services;

import com.bookapplication.bookApplication.entity.BookEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface BookService {

    public void addBook(BookEntity book);

    public List<BookEntity> getAllBooks();

    public BookEntity getBook(Integer bookId);

    public void update(BookEntity book);

    public void delete(BookEntity book);

}

