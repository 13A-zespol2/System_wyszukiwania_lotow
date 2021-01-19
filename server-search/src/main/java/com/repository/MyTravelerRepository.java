package com.repository;

import com.repository.model.database.MyTraveler;
import org.springframework.data.repository.CrudRepository;

public interface MyTravelerRepository extends CrudRepository<MyTraveler, Integer> {
}
