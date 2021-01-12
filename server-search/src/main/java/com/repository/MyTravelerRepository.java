package com.repository;

import com.repository.model.database.MyTraveler;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MyTravelerRepository extends CrudRepository<MyTraveler, Integer> {
    @Override
    Optional<MyTraveler> findById(Integer integer);


}

