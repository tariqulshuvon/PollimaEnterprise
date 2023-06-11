package WebApp.Enterprise.Pollima.controller;

import WebApp.Enterprise.Pollima.form.CompanyForm;
import WebApp.Enterprise.Pollima.model.Company;
import WebApp.Enterprise.Pollima.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/company")
@AllArgsConstructor
@Setter
public class CompanyController {



    private CompanyService companyService;

    @GetMapping
    public String company(Model model, @PageableDefault(size = 2) Pageable pageable) {
        Page<Company> companyPage = companyService.findAll(pageable);
        List<Company> companyList = companyPage.getContent();
        model.addAttribute("companyList", companyList);
        model.addAttribute("saveNewCompany", CompanyForm.builder().build());
        model.addAttribute("companyPage", companyPage);
        return "company";
    }

    @PostMapping
    public String saveCompany(@Valid @ModelAttribute("saveNewCompany") CompanyForm form, BindingResult result){

        if (form.getID()==null) {
            companyService.findByCompanyName(form.getCompanyName()).ifPresent(company ->
                    result.rejectValue("companyName", "error.company", form.getCompanyName() + " already Exist, add a new Company")
            );
        }

        if (result.hasErrors()){
            return "company";
        }

        companyService.save(Company.builder()
                .ID(form.getID())
                .companyName(form.getCompanyName())
                .contactPerson(form.getContactPerson())
                .office(form.getOffice())
                .contactNo(form.getContactNo())
                .build());
        return "redirect:/company";
    }

    @GetMapping("/delete")
    public String deleteCompany(@RequestParam(name = "id") long id){
        companyService.delete(id);
        return "redirect:/company";
    }

    @GetMapping("/edit/{id}")
    public String editCompany(Model model,@PathVariable(name = "id")long id) {
        companyService.findById(id).ifPresent(company -> {
            CompanyForm companyForm = CompanyForm.builder()
                    .ID(company.getID())
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
