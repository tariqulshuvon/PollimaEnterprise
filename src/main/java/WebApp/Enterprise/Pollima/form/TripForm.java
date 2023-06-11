package WebApp.Enterprise.Pollima.form;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TripForm {
    private Long id;
    private Long companyId;
    private Long cargoId;
    private String startDate;
    private String endDate;
    private String fromLocation;
    private String toLocation;
    private Double rent;
}
