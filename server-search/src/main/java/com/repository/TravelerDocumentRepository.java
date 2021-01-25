package com.repository;

import com.repository.model.database.TravelerDocument;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfejs pozwalający na tworzenie zapytań do bazy na podstawie nazw metod do tabeli ,,TravelerDocument".
 */
public interface TravelerDocumentRepository extends CrudRepository<TravelerDocument, Integer> {
    @Override
    Iterable<TravelerDocument> findAll();

    TravelerDocument findByMyTravelerId(int id);
}
