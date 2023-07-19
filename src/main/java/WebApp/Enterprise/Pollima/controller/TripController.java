package WebApp.Enterprise.Pollima.controller;

import WebApp.Enterprise.Pollima.form.TripForm;
import WebApp.Enterprise.Pollima.model.Trip;
import WebApp.Enterprise.Pollima.service.CargoService;
import WebApp.Enterprise.Pollima.service.CompanyService;
import WebApp.Enterprise.Pollima.service.TripService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/trip")
@AllArgsConstructor
@Setter
public class TripController {

    private TripService tripService;
    private CompanyService companyService;
    private CargoService cargoService;
    private MessageSource messageSource;

    @GetMapping
    public String showTrips(Model model, @PageableDefault(size = 2) Pageable pageable) {
        Page<Trip> tripPage = tripService.findAll(pageable);

        List<Trip> trips = tripPage.getContent();
        model.addAttribute("allTrip", trips);
        model.addAttribute("addNewTrip", TripForm.builder().build());
        model.addAttribute("allCompany", companyService.findAll());
        model.addAttribute("allCargo", cargoService.findAll());
        model.addAttribute("tripPage", tripPage);

        return "trip";
    }

    @PostMapping
    public String saveTrip(@Valid @ModelAttribute("addNewTrip") TripForm tripForm, BindingResult result, Model model, @PageableDefault(size = 2) Pageable pageable) {

        String message = messageSource.getMessage("end.date.exist", null, Locale.getDefault());
        if (!result.hasFieldErrors("startDate") && !result.hasFieldErrors("endDate")) {
            LocalDate startDate = LocalDate.parse(tripForm.getStartDate());
            LocalDate endDate = LocalDate.parse(tripForm.getEndDate());

            if (endDate.isBefore(startDate)) {
                result.rejectValue("endDate", "trip.endDate.beforeStartDate", message);
            }
        }



        Long tripId = tripForm.getId();
        Long companyId = tripForm.getCompanyId();
        Long cargoId = tripForm.getCargoId();

        if (companyId == null && cargoId == null && tripId == null) {
            return "trip";
        }

        if (result.hasErrors()) {
            Page<Trip> tripPage = tripService.findAll(pageable);
            List<Trip> trips = tripPage.getContent();

            model.addAttribute("allTrip", trips);
            model.addAttribute("addNewTrip", tripForm);
            model.addAttribute("allCompany", companyService.findAll());
            model.addAttribute("allCargo", cargoService.findAll());
            model.addAttribute("tripPage", tripPage);

            return "/trip";
        }

        if (companyId != null) {
            companyService.findById(companyId).ifPresent(company -> {
                if (cargoId != null) {
                    cargoService.findById(cargoId).ifPresent(cargo -> {
                        tripService.save(Trip.builder()
                                .id(tripForm.getId())
                                .company(company)
                                .cargo(cargo)
                                .startDate(tripForm.getStartDate())
                                .endDate(tripForm.getEndDate())
                                .fromLocation(tripForm.getFromLocation())
                                .toLocation(tripForm.getToLocation())
                                .rent(tripForm.getRent())
                                .build());
                    });
                }
            });
        }

        return "redirect:/trip";
    }

    @PostMapping("/delete")
    public String deleteTrip(@RequestParam(name = "id") long id) {
        tripService.delete(id);
        return "redirect:/trip";
    }

    @GetMapping("/edit")
    public String editTrip(Model model, @RequestParam(name = "id") long id,@PageableDefault(size = 2) Pageable pageable) {
        tripService.findById(id).ifPresent(trip -> {
            TripForm tripForm = TripForm.builder()
                    .id(trip.getId())
                    .companyId(trip.getCompany().getID())
                    .cargoId(trip.getCargo().getID())
                    .startDate(trip.getStartDate())
                    .endDate(trip.getEndDate())
                    .fromLocation(trip.getFromLocation())
                    .toLocation(trip.getToLocation())
                    .rent(trip.getRent())
                    .build();

            model.addAttribute("addNewTrip", tripForm);
            model.addAttribute("allCompany", companyService.findAll());
            model.addAttribute("allCargo", cargoService.findAll());
        });
        Page<Trip> tripPage = tripService.findAll(pageable);
        List<Trip> trips = tripPage.getContent();
        model.addAttribute("allTrip", trips);
        model.addAttribute("tripPage", tripPage);


        return "trip";
    }
}
