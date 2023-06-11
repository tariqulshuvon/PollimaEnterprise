package WebApp.Enterprise.Pollima.form;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Builder
@Data
public class CargoForm {

    private Long id;

    @NotBlank(message = "You must Enter a Cargo name")
    private String cargoName;

    @NotBlank(message = "Please provide the Proprietor")
    private String proprietor;

    private String contact;

    private String address;

    private String reference;

    @NotBlank(message = "Please Select the Contract Start Date")
    private String contactStartDate;
}
