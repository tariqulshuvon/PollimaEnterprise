package WebApp.Enterprise.Pollima.service.implementation;

import WebApp.Enterprise.Pollima.model.Trip;
import WebApp.Enterprise.Pollima.repository.TripRepository;
import WebApp.Enterprise.Pollima.service.TripService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Setter
public class TripServiceImpl implements TripService {

    private TripRepository tripRepository;

    @Override
    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    @Override
    public Optional<Trip> findById(Long id) {
        return tripRepository.findById(id);
    }

    @Override
    public void save(Trip trip) {
        tripRepository.save(trip);
    }
}
