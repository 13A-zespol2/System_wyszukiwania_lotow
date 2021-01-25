package com.repository;

import com.repository.model.database.MyTraveler;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


/**
 * Interfejs pozwalający na tworzenie zapytań do bazy na podstawie nazw metod do tabeli ,,My_traveler".
 */
public interface MyTravelerRepository extends CrudRepository<MyTraveler, Integer> {
    MyTraveler findByUserId(Integer id);

    @Override
    Optional<MyTraveler> findById(Integer integer);

    @Override
    Iterable<MyTraveler> findAll();
}
