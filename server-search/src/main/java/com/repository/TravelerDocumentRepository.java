package com.repository;

import com.repository.model.database.TravelerDocument;
import org.springframework.data.repository.CrudRepository;

public interface TravelerDocumentRepository extends CrudRepository<TravelerDocument, Integer> {
    @Override
    Iterable<TravelerDocument> findAll();

    TravelerDocument findByMyTravelerId(int id);
}
