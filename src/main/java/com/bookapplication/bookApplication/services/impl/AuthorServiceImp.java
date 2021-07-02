package com.bookapplication.bookApplication.services.impl;

import com.bookapplication.bookApplication.entity.AuthorsEntity;
import com.bookapplication.bookApplication.entity.BookEntity;
import com.bookapplication.bookApplication.services.AuthorService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@Transactional
public class AuthorServiceImp implements AuthorService {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookStorePU");
    EntityManager entityManager =entityManagerFactory.createEntityManager();

    @Override
    public void addAuthor(AuthorsEntity authorsEntity) {
        if (!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        entityManager.persist(authorsEntity);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<AuthorsEntity> getAllAuthors() {
        return entityManager.createQuery("select a from AuthorsEntity a").getResultList();
    }

    @Override
    public AuthorsEntity getAuthor(Integer authorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuthorsEntity> cq = cb.createQuery(AuthorsEntity.class);
        Root<AuthorsEntity> author = cq.from(AuthorsEntity.class);
        cq.select(author);
        cq.where(cb.equal(author.get("author_id"), authorId));
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public void update(AuthorsEntity authorsEntity) {
        if (!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }        entityManager.merge(authorsEntity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(AuthorsEntity authorsEntity) {
        if (!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        entityManager.remove(authorsEntity);
        entityManager.getTransaction().commit();
    }

    @Override
    public Map<String, String> getBooksByAuthor() {
        Map<String,String> mapp = new HashMap<>();
        List authorList = entityManager.createQuery("select distinct a.authorName from AuthorsEntity a").getResultList();
        for (Object name : authorList){
            String authorName = name.toString();
            TypedQuery<BookEntity> bookQuery = entityManager.createQuery("select b from BookEntity b WHERE b.bookAuthorName =:authorNameParam",BookEntity.class);
            bookQuery.setParameter("authorNameParam",authorName);
            List<BookEntity> bookList = bookQuery.getResultList();
            StringBuilder bookNames = new StringBuilder();
            for (BookEntity books :bookList){
                bookNames.append(books.getBookName());
                bookNames.append(", ");
            }
            mapp.put(authorName,bookNames.toString());
        }
        return mapp;
    }
}
