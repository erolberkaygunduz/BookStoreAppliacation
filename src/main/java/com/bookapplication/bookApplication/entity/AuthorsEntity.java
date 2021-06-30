package com.bookapplication.bookApplication.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "authors", schema = "book_store")
public class AuthorsEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int authorId;

    @Column(name = "author_name")
    private String authorName;

   // @OneToMany(cascade = CascadeType.ALL,targetEntity = BookEntity.class)
   // @JoinColumn(name = "author_id")
   // private Set<BookEntity> bookEntityList;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

  //  public void addBook(BookEntity book){
  //      bookEntityList.add(book);
  //      book.setAuthorsEntity(this);
  //  }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorsEntity that = (AuthorsEntity) o;
        return authorId == that.authorId && Objects.equals(authorName, that.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, authorName);
    }

  //  public Set<BookEntity> getBookEntityList() {
  //      return bookEntityList;
  //  }

  //  public void setBookEntityList(Set<BookEntity> bookEntityList) {
  //      this.bookEntityList = bookEntityList;
  //  }

}


