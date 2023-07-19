package WebApp.Enterprise.Pollima.form;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class TripForm {
    private Long id;
    @NotNull(message = "{company.name}")
    private Long companyId;
    @NotNull(message = "{cargo.name.required}")
    private Long cargoId;
    @NotBlank(message = "{trip.start.date}")
    private String startDate;
    private String endDate;
    @NotBlank(message = "{trip.starting.location}")
    private String fromLocation;
    @NotBlank(message = "{trip.ending.location}")
    private String toLocation;
    @NotNull(message = "{trip.rent}")
    private Double rent;
}
