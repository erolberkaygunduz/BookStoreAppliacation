package com.bookapplication.bookApplication.controller;

import com.bookapplication.bookApplication.entity.AuthorsEntity;
import com.bookapplication.bookApplication.entity.BookEntity;
import com.bookapplication.bookApplication.services.AuthorService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AuthorBean implements Serializable{

    @EJB
    private AuthorService authorService;

    private List<AuthorsEntity> authorsList;

    private AuthorsEntity authorsEntity = new AuthorsEntity();

    @PostConstruct
    public void init() {
        this.authorsList = this.authorService.getAllAuthors();
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




    public List<AuthorsEntity> getAuthorsList() {
        return authorsList;
    }

    public void setAuthorsList(List<AuthorsEntity> authorsList) {
        this.authorsList = authorsList;
    }

    public AuthorsEntity getAuthorsEntity() {
        return authorsEntity;
    }

    public void setAuthorsEntity(AuthorsEntity authorsEntity) {
        this.authorsEntity = authorsEntity;
    }
}
