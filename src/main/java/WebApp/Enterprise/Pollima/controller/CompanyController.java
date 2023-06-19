package WebApp.Enterprise.Pollima.controller;

import WebApp.Enterprise.Pollima.form.CompanyForm;
import WebApp.Enterprise.Pollima.model.Company;
import WebApp.Enterprise.Pollima.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

@Controller
@RequestMapping("/company")
@AllArgsConstructor
@Setter
public class CompanyController {



    private MessageSource messageSource;
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
    private String saveCompany(@Valid @ModelAttribute("saveNewCompany") CompanyForm form, BindingResult result){
        String messageToShow = messageSource.getMessage("company.exist",null, LocaleContextHolder.getLocale());
        String companyName = form.getCompanyName();
        String theMessage = MessageFormat.format(messageToShow,companyName);

        if (form.getId()==null) {
            companyService.findByCompanyName(form.getCompanyName()).ifPresent(company ->
                    result.rejectValue("companyName", "error.company", theMessage)
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
