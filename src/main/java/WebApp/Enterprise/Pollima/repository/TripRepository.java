package WebApp.Enterprise.Pollima.repository;

import WebApp.Enterprise.Pollima.model.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    @Override
    Page<Trip> findAll(Pageable pageable);
    @Query("select t from Trip t join t.company cm join t.cargo c order by t.startDate desc")
    List<Trip> findLatestTrip(int count);
}
