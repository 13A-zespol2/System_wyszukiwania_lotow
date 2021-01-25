package com.repository;


import com.repository.model.database.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfejs pozwalający na tworzenie zapytań do bazy na podstawie nazw metod do tabeli ,,User".
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByEmailAndPassword(String email, String password);
}