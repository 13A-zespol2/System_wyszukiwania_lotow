package com.repository;

import com.repository.model.database.UserPhone;
import org.springframework.data.repository.CrudRepository;

public interface UserPhoneRepository extends CrudRepository<UserPhone, Integer> {
}
