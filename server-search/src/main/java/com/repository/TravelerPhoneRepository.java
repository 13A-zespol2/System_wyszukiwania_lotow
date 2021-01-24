package com.repository;

import com.repository.model.database.TravelerPhone;
import org.springframework.data.repository.CrudRepository;

public interface TravelerPhoneRepository extends CrudRepository<TravelerPhone, Integer> {
    TravelerPhone findById(int id);
}
