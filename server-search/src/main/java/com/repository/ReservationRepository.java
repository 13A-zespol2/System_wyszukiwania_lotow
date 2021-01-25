package com.repository;

import com.repository.model.database.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Interfejs pozwalający na tworzenie zapytań do bazy na podstawie nazw metod do tabeli ,,Reservation".
 */
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    List<Reservation> findByMyTravelerId(int id);
}
