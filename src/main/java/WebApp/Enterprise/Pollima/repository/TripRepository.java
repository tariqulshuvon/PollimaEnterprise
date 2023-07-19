package WebApp.Enterprise.Pollima.repository;

import WebApp.Enterprise.Pollima.model.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    @Override
    Page<Trip> findAll(Pageable pageable);
}
