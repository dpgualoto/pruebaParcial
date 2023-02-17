package com.programacion.distribuida.servicios;

import com.programacion.distribuida.db.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    Author findID(Long id);
    void insert(Author author);
    void update(Long id, Author author);
    void delete(Long id);
}
