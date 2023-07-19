package WebApp.Enterprise.Pollima.repository;

import WebApp.Enterprise.Pollima.model.Cargo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    Optional<Cargo> findByCargoName(String name);
    @Override
    Page<Cargo> findAll(Pageable pageable);
}
