package com.repository;

import com.repository.model.database.UserCreditCard;
import org.springframework.data.repository.CrudRepository;

public interface UserCreditCardRepository extends CrudRepository<UserCreditCard, Integer> {
}
