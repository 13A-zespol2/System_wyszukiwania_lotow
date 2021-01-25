package com.repository;

import com.repository.model.database.TravelerPhone;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfejs pozwalający na tworzenie zapytań do bazy na podstawie nazw metod do tabeli ,,TravelerPhone".
 */
public interface TravelerPhoneRepository extends CrudRepository<TravelerPhone, Integer> {
    TravelerPhone findById(int id);
}
