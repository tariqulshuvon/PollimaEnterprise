package WebApp.Enterprise.Pollima.form;

import WebApp.Enterprise.Pollima.model.Cargo;
import WebApp.Enterprise.Pollima.model.Trip;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VoucherForm {
    private Long id;
    private Long tripId;
    private String voucherNo;
    private Long cargoId;
    private String date;
    private Double dr;
    private Double cr;
    private String particular;

}
