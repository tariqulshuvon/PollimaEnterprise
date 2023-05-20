package WebApp.Enterprise.Pollima.controller;

import WebApp.Enterprise.Pollima.form.TripForm;
import WebApp.Enterprise.Pollima.model.Cargo;
import WebApp.Enterprise.Pollima.model.Company;
import WebApp.Enterprise.Pollima.model.Trip;
import WebApp.Enterprise.Pollima.model.Voucher;
import WebApp.Enterprise.Pollima.service.CargoService;
import WebApp.Enterprise.Pollima.service.CompanyService;
import WebApp.Enterprise.Pollima.service.TripService;
import WebApp.Enterprise.Pollima.service.VoucherService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/report")
@AllArgsConstructor
@Setter
public class ReportController {
    private TripService tripService;
    private CargoService cargoService;
    private CompanyService companyService;
    private VoucherService voucherService;
    @GetMapping
    public String showReport(Model model)
    {
        List<Trip> tripList = tripService.findAll();
        List<Cargo> cargoList = cargoService.findAll();
        List<Company> companyList = companyService.findAll();
        List<Voucher> voucherList = voucherService.findAll();
        model.addAttribute("allTrips", tripList);
        model.addAttribute("allCargo", cargoList);
        model.addAttribute("allCompany", companyList);
        model.addAttribute("allVoucher", voucherList);

        return "report";
    }
}
