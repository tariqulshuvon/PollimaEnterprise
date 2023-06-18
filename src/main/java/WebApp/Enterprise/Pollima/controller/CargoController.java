package WebApp.Enterprise.Pollima.controller;

import WebApp.Enterprise.Pollima.form.CargoForm;
import WebApp.Enterprise.Pollima.model.Cargo;
import WebApp.Enterprise.Pollima.service.CargoService;
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
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Controller
@AllArgsConstructor
@Setter
@RequestMapping("/cargo")
public class CargoController {
    private MessageSource messageSource;
    private CargoService cargoService;

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

        String messageTOShow = messageSource.getMessage("cargo.exist",null, Locale.getDefault());
        String theMessage = MessageFormat.format(messageTOShow,form.getCargoName());

        if (form.getId() == null) {
            cargoService.findByCargoName(form.getCargoName()).ifPresent(cargo ->
                result.rejectValue("cargoName", "error.cargo", theMessage)

            );
        } else if (form.getId() != null) {
            cargoService.findByCargoName(form.getCargoName()).ifPresent(cargo -> {
                if ( (!Objects.equals(form.getId(), cargo.getID())) && (Objects.equals(form.getCargoName(), cargo.getCargoName()))){
                    result.rejectValue("cargoName","error.cargo",theMessage);
                }
            });

        }
        if (result.hasErrors()){
            return "cargo";
        }

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

    @GetMapping("/edit/")
    public String editCargo(Model model, @RequestParam(name = "id")long id){
        cargoService.findById(id).ifPresent(cargo -> {
            CargoForm cargoForm = CargoForm.builder()
                    .id(cargo.getID())
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
