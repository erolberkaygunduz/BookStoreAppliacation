package com.bookapplication.bookApplication.services;

import com.bookapplication.bookApplication.entity.AuthorsEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

@Local
public interface AuthorService {

    public void addAuthor(AuthorsEntity authorsEntity);

    public List<AuthorsEntity> getAllAuthors();

    public AuthorsEntity getAuthor(Integer authorId);

    public void update(AuthorsEntity authorsEntity);

    public void delete(AuthorsEntity authorsEntity);

    public Map<String, String> getBooksByAuthor();
}
