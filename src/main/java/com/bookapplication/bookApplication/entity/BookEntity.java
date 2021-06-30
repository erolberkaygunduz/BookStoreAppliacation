package com.bookapplication.bookApplication.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "book", schema = "book_store")
public class BookEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    @Column(name = "bookName")
    private String bookName;

    @Column(name = "price")
    private Double price;

    @Column(name = "genre")
    private String genre;

    @Column(name = "bookAuthorName")
    private String bookAuthorName;

    //@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    //@JoinColumn(name = "author_id", referencedColumnName = "author_id")
    //private AuthorsEntity authorsEntity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_fk"), inverseJoinColumns = @JoinColumn(name = "author_fk"))
    private Set<AuthorsEntity> authorsEntity = new HashSet<>();

    public Set<AuthorsEntity> getAuthorsEntity() {
        return authorsEntity;
    }

    public void setAuthorsEntity(Set<AuthorsEntity> authorsEntity) {
        this.authorsEntity = authorsEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBookAuthorName() {
        return bookAuthorName;
    }

    public void setBookAuthorName(String bookAuthorName) {
        this.bookAuthorName = bookAuthorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return id == that.id && bookName.equals(that.bookName) && price.equals(that.price) && genre.equals(that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookName, price, genre);
    }
}
