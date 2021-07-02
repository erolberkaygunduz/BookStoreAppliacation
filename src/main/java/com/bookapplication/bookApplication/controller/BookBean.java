package com.bookapplication.bookApplication.controller;

import com.bookapplication.bookApplication.entity.AuthorsEntity;
import com.bookapplication.bookApplication.entity.BookEntity;
import com.bookapplication.bookApplication.services.AuthorService;
import com.bookapplication.bookApplication.services.BookService;
import org.primefaces.event.CellEditEvent;

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
public class BookBean implements Serializable {

    @EJB
    private BookService bookService;
    @EJB
    private AuthorService authorService;


    private List<BookEntity> bookList ;
    private List<AuthorsEntity> authorsList;

    private BookEntity bookEntity =new BookEntity();

    private AuthorsEntity authorsEntity = new AuthorsEntity();

    @PostConstruct
    public void init() {
        this.bookList = this.bookService.getAllBooks();
        this.authorsList = this.authorService.getAllAuthors();

    }

    public void addNewBook() {
        this.bookEntity = new BookEntity();
    }

    public void deleteBook(){
        this.bookService.delete(bookEntity);
    }

    public void createBook(){
        bookEntity.getAuthorsEntity().add(authorsEntity);
        bookEntity.setBookAuthorName(authorsEntity.getAuthorName());
        this.bookService.addBook(bookEntity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Kitap eklendi."));
    }

    public void updateBook(){
        this.bookService.update(bookEntity);
        //this.authorsEntity.setAuthorName(bookEntity.getBookAuthorName());
        this.authorService.update(authorsEntity);

    }

    public List<BookEntity> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookEntity> bookList) {
        this.bookList = bookList;
    }

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }

    public AuthorsEntity getAuthorsEntity() {
        return authorsEntity;
    }

    public void setAuthorsEntity(AuthorsEntity authorsEntity) {
        this.authorsEntity = authorsEntity;
    }

    public List<AuthorsEntity> getAuthorsList() {
        return authorsList;
    }

    public void setAuthorsList(List<AuthorsEntity> authorsList) {
        this.authorsList = authorsList;
    }
}