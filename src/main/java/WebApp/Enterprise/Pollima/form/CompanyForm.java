package WebApp.Enterprise.Pollima.form;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class CompanyForm {
    private Long id;
    @NotBlank(message = "{company.name}")
    private String companyName;
    private String contactPerson;
    private String office;
    @Pattern(regexp = "^$|(^\\+?8801|8801|01)[3-9]\\d{8}$", message = "{contact.no.pattern}")
    @Size(max = 15, message = "{contact.no.size}")
    private String contactNo;
}
