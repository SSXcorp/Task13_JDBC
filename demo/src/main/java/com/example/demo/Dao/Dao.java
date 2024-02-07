package com.example.demo.Dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(long id); //read

    List<T> getAll();

    void save(T t); //create

    void update(T t);

    void delete(int id);
}
