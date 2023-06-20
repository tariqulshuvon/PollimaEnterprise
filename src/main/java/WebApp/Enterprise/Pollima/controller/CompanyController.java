package WebApp.Enterprise.Pollima.controller;

import WebApp.Enterprise.Pollima.form.CompanyForm;
import WebApp.Enterprise.Pollima.model.Company;
import WebApp.Enterprise.Pollima.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public String company(Model model, @PageableDefault(size = 2) Pageable pageable) {
        model.addAttribute("companyList", companyService.findAll(pageable).getContent());
        model.addAttribute("saveNewCompany", CompanyForm.builder().build());
        model.addAttribute("companyPage", companyService.findAll(pageable));
        return "company";
    }


    @PostMapping
    private String saveCompany(@Valid @ModelAttribute("saveNewCompany") CompanyForm form, BindingResult result){

        if (form.getId()==null) {
            companyService.findByCompanyName(form.getCompanyName()).ifPresent(company ->
                    result.rejectValue("companyName", "company.exist", new Object[]{form.getCompanyName()}, "company.exist")
            );
        }

        if (result.hasErrors()){
            return "company";
        }

        companyService.save(Company.builder()
                .id(form.getId())
                .companyName(form.getCompanyName())
                .contactPerson(form.getContactPerson())
                .office(form.getOffice())
                .contactNo(form.getContactNo())
                .build());
        return "redirect:/company";
    }

    @GetMapping("/delete")
    private String deleteCompany(@RequestParam(name = "id") long id){
        companyService.delete(id);
        return "redirect:/company";
    }

    @GetMapping("/edit")
    private String editCompany(Model model,@RequestParam(name = "id")long id) {
        companyService.findById(id).ifPresent(company -> {
            CompanyForm companyForm = CompanyForm.builder()
                    .id(company.getId())
                    .companyName(company.getCompanyName())
                    .contactPerson(company.getContactPerson())
                    .office(company.getOffice())
                    .contactNo(company.getContactNo())
                    .build();
            model.addAttribute("saveNewCompany",companyForm);
        });
        return "company";
    }
}
