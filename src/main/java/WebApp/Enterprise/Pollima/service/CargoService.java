package WebApp.Enterprise.Pollima.service;


import WebApp.Enterprise.Pollima.model.Cargo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CargoService {

    List<Cargo> findAll();

    Optional<Cargo> findById(Long id);
    Optional<Cargo> findByCargoName(String name);

    void save(Cargo cargo);

    void delete(Long id);

    Page<Cargo> findAll(Pageable pageable);
}
