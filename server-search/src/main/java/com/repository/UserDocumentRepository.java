package com.repository;

import com.repository.model.database.UserDocument;
import org.springframework.data.repository.CrudRepository;

public interface UserDocumentRepository extends CrudRepository<UserDocument, Integer> {
}
