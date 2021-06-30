package com.bookapplication.bookApplication.services;

import com.bookapplication.bookApplication.entity.AuthorsEntity;
import com.bookapplication.bookApplication.entity.BookEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AuthorService {

    public void addAuthor(AuthorsEntity authorsEntity);

    public List<AuthorsEntity> getAllAuthors();

    public AuthorsEntity getAuthor(Integer authorId);

    public void update(AuthorsEntity authorsEntity);

    public void delete(AuthorsEntity authorsEntity);

    public List<BookEntity> getBooksByAuthor(String authorName);
}
