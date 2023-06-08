package WebApp.Enterprise.Pollima.form;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Data
@Builder
public class CompanyForm {
    private Long ID;
    @NotBlank(message = "Company Name is Required")
    private String companyName;
    private String contactPerson;
    private String office;
    @Pattern(regexp = "^$|(^\\+?8801|8801|01)[3-9]\\d{8}$", message = "Please enter a valid Bangladeshi number")
    @Size(max = 15, message = "A Contact Number can not be more than 15 digits")
    private String contactNo;
}
