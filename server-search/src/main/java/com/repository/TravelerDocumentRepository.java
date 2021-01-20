package com.repository;

import com.repository.model.database.TravelerDocument;
import com.repository.model.database.User;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface TravelerDocumentRepository extends CrudRepository<TravelerDocument, Integer> {
    //TravelerDocument findByMyTravelerId(Integer id);
}
