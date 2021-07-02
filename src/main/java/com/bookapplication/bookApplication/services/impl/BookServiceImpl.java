package com.bookapplication.bookApplication.services.impl;

import com.bookapplication.bookApplication.entity.BookEntity;
import com.bookapplication.bookApplication.services.BookService;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;

import java.util.List;

@Stateless
@Transactional
public class BookServiceImpl implements BookService {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookStorePU");
    EntityManager entityManager =entityManagerFactory.createEntityManager();

    public BookEntity getBook(Integer bookId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookEntity> cq = cb.createQuery(BookEntity.class);
        Root<BookEntity> book = cq.from(BookEntity.class);
        cq.select(book);
        cq.where(cb.equal(book.get("id"), bookId));
        return entityManager.createQuery(cq).getSingleResult();
    }

    public List<BookEntity> getAllBooks() {
        return entityManager.createQuery("select b from BookEntity b").getResultList();
    }

    public void addBook(BookEntity book) {
        if (!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        entityManager.persist(book);
        entityManager.getTransaction().commit();

    }

    public void update(BookEntity book) {
        if (!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        //entityManager.merge(book);
        entityManager.getTransaction().commit(); //ilginccc
    }

    public void delete(BookEntity book) {
        if (!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        entityManager.remove(book);
        entityManager.getTransaction().commit();

    }
}