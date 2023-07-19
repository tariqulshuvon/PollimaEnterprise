package WebApp.Enterprise.Pollima.component;

import WebApp.Enterprise.Pollima.form.CompanyForm;
import WebApp.Enterprise.Pollima.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public Company mapToCompany(CompanyForm form) {
        return Company.builder()
                .id(form.getId())
                .companyName(form.getCompanyName())
                .contactPerson(form.getContactPerson())
                .office(form.getOffice())
                .contactNo(form.getContactNo())
                .build();
    }

    public CompanyForm mapToForm(Company company) {
        return CompanyForm.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .contactPerson(company.getContactPerson())
                .office(company.getOffice())
                .contactNo(company.getContactNo())
                .build();
    }
}
