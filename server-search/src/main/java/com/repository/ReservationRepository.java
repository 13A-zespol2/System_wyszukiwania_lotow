package com.repository;

import com.repository.model.database.Reservation;
import com.repository.model.database.User;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
}
