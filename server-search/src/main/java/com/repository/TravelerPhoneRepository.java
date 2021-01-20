package com.repository;

import com.repository.model.database.TravelerPhone;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TravelerPhoneRepository extends CrudRepository<TravelerPhone, Integer> {
    Optional<TravelerPhone> findById(Integer id);
}
