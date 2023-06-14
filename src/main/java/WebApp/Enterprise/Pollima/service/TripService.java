package WebApp.Enterprise.Pollima.service;

import WebApp.Enterprise.Pollima.model.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TripService {

    List<Trip> findAll();

    Optional<Trip> findById(Long id);

    void save(Trip trip);
    void delete(Long id);
    Page<Trip> findAll(Pageable pageable);
}
