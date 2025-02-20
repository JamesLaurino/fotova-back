package com.fotova.repository;

import java.util.List;

public interface ICrud<T> {
    T findById(int id);

    List<T> findAll();

    T save(T t);

    void deleteAll();

    void deleteById(int id);

    T update(T t);
}
