package com.repository;

import com.repository.model.database.TravelerDocument;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TravelerDocumentRepository extends CrudRepository<TravelerDocument, Integer> {
    @Override
    Optional<TravelerDocument> findById(Integer integer);
}
