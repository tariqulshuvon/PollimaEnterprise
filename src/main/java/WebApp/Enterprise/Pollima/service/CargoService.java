package WebApp.Enterprise.Pollima.service;


import WebApp.Enterprise.Pollima.model.Cargo;

import java.util.List;
import java.util.Optional;

public interface CargoService {

    List<Cargo> findAll();

    Optional<Cargo> findById(Long id);

    void save(Cargo cargo);
}
