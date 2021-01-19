package com.repository;

import com.repository.model.database.TravelerDocument;
import com.repository.model.database.User;
import org.springframework.data.repository.CrudRepository;

public interface TravelerDocumentRepository extends CrudRepository<TravelerDocument, Integer> {
}