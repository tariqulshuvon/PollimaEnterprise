package WebApp.Enterprise.Pollima.service.implementation;

import WebApp.Enterprise.Pollima.model.Cargo;
import WebApp.Enterprise.Pollima.repository.CargoRepository;
import WebApp.Enterprise.Pollima.service.CargoService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Setter
public class CargoServiceImpl implements CargoService {


    private CargoRepository cargoRepository;

    @Override
    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }

    @Override
    public Optional<Cargo> findById(Long id) {
        return cargoRepository.findById(id);
    }

    @Override
    public Optional<Cargo> findByCargoName(String name) {
        return cargoRepository.findByCargoName(name);
    }

    @Override
    public void save(Cargo cargo) {

        cargoRepository.save(cargo);

    }

    @Override
    public void delete(Long id) {
        cargoRepository.deleteById(id);
    }

    @Override
    public Page<Cargo> findAll(Pageable pageable) {
        return cargoRepository.findAll(pageable);
    }
}
