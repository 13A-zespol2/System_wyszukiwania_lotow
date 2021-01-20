package com.repository;

import com.repository.model.database.MyTraveler;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MyTravelerRepository extends CrudRepository<MyTraveler, Integer> {
    MyTraveler findByUserId(Integer id);

    @Override
    Optional<MyTraveler> findById(Integer integer);

    @Override
    Iterable<MyTraveler> findAll();
}
