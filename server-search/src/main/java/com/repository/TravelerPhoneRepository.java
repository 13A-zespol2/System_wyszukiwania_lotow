package com.repository;

import com.repository.model.database.TravelerPhone;
import com.repository.model.database.User;
import org.springframework.data.repository.CrudRepository;

public interface TravelerPhoneRepository extends CrudRepository<TravelerPhone, Integer> {
}
