package WebApp.Enterprise.Pollima.controller;

import WebApp.Enterprise.Pollima.component.CompanyMapper;
import WebApp.Enterprise.Pollima.form.CompanyForm;
import WebApp.Enterprise.Pollima.model.Company;
import WebApp.Enterprise.Pollima.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/company")
@AllArgsConstructor
@Setter
public class CompanyController {


    private CompanyService companyService;
    private CompanyMapper companyMapper;

    @GetMapping
    public String company(Model model, Pageable pageable) {
        model.addAttribute("companyForm", CompanyForm.builder().build());
        model.addAttribute("companyPage", companyService.findAll(pageable));
        return "company";
    }


    @PostMapping
    private String saveCompany(@Valid @ModelAttribute("companyForm") CompanyForm companyForm, BindingResult result, Model model, Pageable pageable) {

        if (companyForm.getId() == null) {
            companyService.findByCompanyName(companyForm.getCompanyName()).ifPresent(company ->
                    result.rejectValue("companyName", "company.exist", new Object[]{companyForm.getCompanyName()}, "company.exist")
            );
        }

        if (result.hasErrors()) {
            model.addAttribute("companyPage", companyService.findAll(pageable));
            return "company";
        }

        Company company = companyMapper.mapToCompany(companyForm);
        companyService.save(company);
        return "redirect:/company";
    }

    @GetMapping("/delete")
    private String deleteCompany(@RequestParam(name = "id") long id) {
        companyService.findById(id).ifPresent(company -> {
            companyService.delete(company);
        });
        return "redirect:/company";
    }

    @GetMapping("/edit")
    private String editCompany(Model model, @RequestParam(name = "id") long id, Pageable pageable) {
        companyService.findById(id).ifPresent(company -> {
            CompanyForm companyForm = companyMapper.mapToForm(company);
            model.addAttribute("companyForm", companyForm);
            model.addAttribute("companyPage", companyService.findAll(pageable));
        });
        return "company";
    }
}
