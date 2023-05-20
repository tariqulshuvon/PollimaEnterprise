package WebApp.Enterprise.Pollima.controller;

import WebApp.Enterprise.Pollima.form.TripForm;
import WebApp.Enterprise.Pollima.model.Trip;
import WebApp.Enterprise.Pollima.repository.CargoRepository;
import WebApp.Enterprise.Pollima.repository.CompanyRepository;
import WebApp.Enterprise.Pollima.repository.TripRepository;
import WebApp.Enterprise.Pollima.service.CargoService;
import WebApp.Enterprise.Pollima.service.CompanyService;
import WebApp.Enterprise.Pollima.service.TripService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/trip")
@AllArgsConstructor
@Setter
public class TripController {

    private TripService tripService;
    private CompanyService companyService;
    private CargoService cargoService;

    @GetMapping
    public String showTrips(Model model){
        List<Trip> trips = tripService.findAll();
        model.addAttribute("allTrip",trips);
        model.addAttribute("addNewTrip", TripForm.builder().build());
        model.addAttribute("allCompany",companyService.findAll());
        model.addAttribute("allCargo",cargoService.findAll());
        return "trip";
    }

    @PostMapping
    private String saveTrip(@ModelAttribute("saveNewTrip") TripForm tripForm) {

        cargoService.findById(tripForm.getCargoId()).ifPresent(cargo -> {
            tripService.save(Trip.builder()
                            .id(tripForm.getId())
                            .company(cargo.getCompany())
                            .cargo(cargo)
                            .startDate(tripForm.getStartDate())
                            .endDate(tripForm.getEndDate())
                            .fromLocation(tripForm.getFromLocation())
                            .toLocation(tripForm.getToLocation())
                            .rent(tripForm.getRent())
                    .build());
        });

        return "redirect:/trip";
    }
}
