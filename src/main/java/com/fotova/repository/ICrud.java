package com.fotova.repository;

import java.util.List;

public interface ICrud<T> {
    T findById(int id);

    List<T> findAll();

    T save(T t);

    void delete(T t);

    T update(T t);
}
