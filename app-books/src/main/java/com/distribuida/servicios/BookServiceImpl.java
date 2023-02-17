package com.distribuida.servicios;

import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class BookServiceImpl implements BookService {
    @PersistenceContext(unitName = "jpa")
    private EntityManager manejador;

    @Override
    public List<Book> findAll()  {
        return manejador
                .createNamedQuery("Book.findAll",Book.class)
                .getResultList();
    }

    @Override
    public Book findId(Integer id){
        return manejador.find(Book.class,id);
    }


    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void insert(Book book) {
        manejador.persist(book);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void update(Integer id, Book book){
        var savedBook=this.findId(id);
        savedBook.setIsbn(book.getIsbn());
        savedBook.setAuthor(book.getAuthor());
        savedBook.setTitle(book.getTitle());
        savedBook.setPrice(book.getPrice());
        manejador.persist(book);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Integer id) {
        manejador.remove(findId(id));
    }

}
