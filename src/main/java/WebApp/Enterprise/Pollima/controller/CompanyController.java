package WebApp.Enterprise.Pollima.controller;

import WebApp.Enterprise.Pollima.form.CompanyForm;
import WebApp.Enterprise.Pollima.model.Company;
import WebApp.Enterprise.Pollima.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/company")
@AllArgsConstructor
@Setter
public class CompanyController {



    private CompanyService companyService;

    @GetMapping
    public String company(Model model){
        List<Company> companyList = companyService.findAll();
        model.addAttribute("CompanyList",companyList);
        model.addAttribute("AddNewCompany", CompanyForm.builder().build());
        return "company";
    }

    @PostMapping
    private String saveCompany(@ModelAttribute("saveNewCompany") CompanyForm form){
        companyService.save(Company.builder()
                .ID(form.getID())
                .CompanyName(form.getCompanyName())
                .contactPerson(form.getContactPerson())
                .office(form.getOffice())
                .contactNo(form.getContactNo())
                .build());
        return "redirect:/company";
    }

}
