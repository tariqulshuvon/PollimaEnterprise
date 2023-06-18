package WebApp.Enterprise.Pollima.form;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class VoucherForm {
    private Long id;
    @NotNull(message = "{trip.not.null}")
    private Long tripId;
    @NotBlank(message = "{trip.voucher.no}")
    private String voucherNo;
    @NotBlank(message = "{voucher.date}")
    private String date;
    private Double dr;
    private Double cr;
    private String particular;

}
