package com.bookapplication.bookApplication.controller;

import com.bookapplication.bookApplication.entity.AuthorsEntity;
import com.bookapplication.bookApplication.services.AuthorService;
import com.bookapplication.bookApplication.services.BookService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class AuthorBean implements Serializable{

    @EJB
    private AuthorService authorService;
    @EJB
    private BookService bookService;

    private Map<String, String> authorsList;


    private AuthorsEntity authorsEntity = new AuthorsEntity();

    @PostConstruct
    public void init() {
        this.authorsList = this.authorService.getBooksByAuthor();
    }

    public void addNewAuthor() {
        this.authorsEntity = new AuthorsEntity();
    }

    public void deleteAuthor(){
        this.authorService.delete(authorsEntity);
    }

    public void createAuthor(){
        this.authorService.addAuthor(authorsEntity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Yazar eklendi."));
    }

    public void updateAuthor(){
        this.authorService.update(authorsEntity);

    }

    public List<Map.Entry<String, String>> getAuthorsList() {
        List<Map.Entry<String,String>> entryMap;
        entryMap = new ArrayList<>(authorsList.entrySet());
        return entryMap;
    }

    public void setAuthorsList(Map<String, String> authorsList) {
        this.authorsList = authorsList;
    }

    public AuthorsEntity getAuthorsEntity() {
        return authorsEntity;
    }

    public void setAuthorsEntity(AuthorsEntity authorsEntity) {
        this.authorsEntity = authorsEntity;
    }
}
