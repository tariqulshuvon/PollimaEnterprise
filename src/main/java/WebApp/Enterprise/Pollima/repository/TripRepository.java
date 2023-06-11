package WebApp.Enterprise.Pollima.repository;

import WebApp.Enterprise.Pollima.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {


}
