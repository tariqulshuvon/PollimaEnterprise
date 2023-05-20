package WebApp.Enterprise.Pollima.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyForm {
    private Long ID;
    private String CompanyName;
    private String contactPerson;
    private String office;
    private String contactNo;
}
