package WebApp.Enterprise.Pollima.controller;

import WebApp.Enterprise.Pollima.form.CargoForm;
import WebApp.Enterprise.Pollima.model.Cargo;
import WebApp.Enterprise.Pollima.service.CargoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cargo")
public class CargoController {
    private CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    public void setCargoService(CargoService cargoService) {
        this.cargoService = cargoService;
    }


    @GetMapping
    public String showCargoPage(Model model, @PageableDefault(size = 2) Pageable pageable){
        Page<Cargo> cargoPage = cargoService.findAll(pageable);
        List<Cargo> cargoList = cargoPage.getContent();
        model.addAttribute("allCargo",cargoList);
        model.addAttribute("saveNewCargo", CargoForm.builder().build());
        model.addAttribute("cargoPage",cargoPage);
        return "cargo";
    }

    @PostMapping
    private String saveCargo(@Valid @ModelAttribute("saveNewCargo") CargoForm form, BindingResult result) {
        if (form.getId() == null) {
            cargoService.findByCargoName(form.getCargoName()).ifPresent(cargo -> {
                result.rejectValue("cargoName", "error.cargo", form.getCargoName()+"already exist, Please add a new Cargo");
            });
        }
        if (result.hasErrors()){
            return "cargo";
        }else {
            cargoService.save(Cargo.builder()
                    .ID(form.getId())
                    .cargoName(form.getCargoName())
                    .Proprietor(form.getProprietor())
                    .Contact(form.getContact())
                    .Address(form.getAddress())
                    .Reference(form.getReference())
                    .ContactStartDate(form.getContactStartDate())
                    .build());

            return "redirect:/cargo";
        }
    }

    @GetMapping("/edit/")
    public String editCargo(Model model, @RequestParam(name = "id")long id){
        cargoService.findById(id).ifPresent(cargo -> {
            CargoForm cargoForm = CargoForm.builder()
                    .cargoName(cargo.getCargoName())
                    .proprietor(cargo.getProprietor())
                    .contact(cargo.getContact())
                    .address(cargo.getAddress())
                    .reference(cargo.getReference())
                    .contactStartDate(cargo.getContactStartDate())
                    .build();
            model.addAttribute("saveNewCargo",cargoForm);
        });

        return "cargo";
    }

    @PostMapping("/delete")
    public String deleteCargo(@RequestParam(name = "id")long id){
        cargoService.delete(id);
        return "redirect:/cargo";
    }

}
