package WebApp.Enterprise.Pollima.form;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Builder
@Data
public class CargoForm {

    private Long id;

    @NotBlank(message = "{cargo.name.required}")
    private String cargoName;

    @NotBlank(message = "{cargo.proprietor.required}")
    private String proprietor;

    private String contact;

    private String address;

    private String reference;

    @NotBlank(message = "{cargo.contact.required}")
    private String contactStartDate;
}
