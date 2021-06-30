package com.bookapplication.bookApplication.services.impl;

import com.bookapplication.bookApplication.entity.AuthorsEntity;
import com.bookapplication.bookApplication.entity.BookEntity;
import com.bookapplication.bookApplication.services.AuthorService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
@Transactional
public class AuthorServiceImp implements AuthorService {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookStorePU");
    EntityManager entityManager =entityManagerFactory.createEntityManager();

    @Override
    public void addAuthor(AuthorsEntity authorsEntity) {
        entityManager.getTransaction().begin();
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
        entityManager.getTransaction().begin();
        if(authorsEntity.getAuthorName() != null){
            entityManager.merge(authorsEntity);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(AuthorsEntity authorsEntity) {
        entityManager.getTransaction().begin();
        entityManager.remove(authorsEntity);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<BookEntity> getBooksByAuthor(String authorName) {
        return entityManager.createQuery("select b,a from BookEntity b left join BookEntity.authorsEntity. ba on b.id = ba. left join AuthorsEntity a on ba.author_fk = a.author_id WHERE\n" +
                "\ta.author_id = 4").getResultList();
    }
}
