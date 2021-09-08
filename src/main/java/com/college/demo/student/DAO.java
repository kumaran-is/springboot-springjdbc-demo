package com.college.demo.student;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> findAll();

    void create(T t);

    Optional<T> findById(Long id);

    void update(T t, Long id);

    void delete(Long id);
    
    Optional<T> findByEmail(String email);

}