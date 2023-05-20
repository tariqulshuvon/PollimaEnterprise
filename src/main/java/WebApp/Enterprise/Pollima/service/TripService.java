package WebApp.Enterprise.Pollima.service;

import WebApp.Enterprise.Pollima.model.Trip;

import java.util.List;
import java.util.Optional;

public interface TripService {

    List<Trip> findAll();

    Optional<Trip> findById(Long id);

    void save(Trip trip);
}
