package WebApp.Enterprise.Pollima.controller;

import WebApp.Enterprise.Pollima.form.CargoForm;
import WebApp.Enterprise.Pollima.model.Cargo;
import WebApp.Enterprise.Pollima.service.CargoService;
import WebApp.Enterprise.Pollima.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cargo")
public class CargoController {
    private CargoService cargoService;
    private CompanyService companyService;

    public void YourController(CargoService cargoService, CompanyService companyService) {
        this.cargoService = cargoService;
        this.companyService = companyService;
    }

    public CargoController(CargoService cargoService, CompanyService companyService) {
        this.cargoService = cargoService;
        this.companyService = companyService;
    }


    @GetMapping
    public String showCargoPage(Model model){
        List<Cargo> cargoList = cargoService.findAll();
        model.addAttribute("allCargo",cargoList);
        model.addAttribute("addNewCargo", CargoForm.builder().build());
        model.addAttribute("companyList", companyService.findAll());
        return "cargo";
    }

    @PostMapping
    private String saveCargo(@ModelAttribute("saveNewCargo") CargoForm form) {
        companyService.findById(form.getCompanyId()).ifPresent(company -> {
        cargoService.save(Cargo.builder()
                .ID(form.getId())
                .CargoName(form.getCargoName())
                .Proprietor(form.getProprietor())
                .Contact(form.getContact())
                .Address(form.getAddress())
                .Reference(form.getReference())
                .ContactStartDate(form.getContactStartDate())
                .company(company)
                .build());
        });

        return "redirect:/cargo";

    }


}
