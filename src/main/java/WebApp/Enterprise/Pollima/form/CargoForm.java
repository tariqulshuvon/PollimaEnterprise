package WebApp.Enterprise.Pollima.form;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class CargoForm {

    private Long id;

    private String cargoName;

    private String proprietor;

    private String contact;

    private String address;

    private String reference;

    private String contactStartDate;

    private Long companyId;
}
