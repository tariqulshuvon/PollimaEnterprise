package WebApp.Enterprise.Pollima.controller;

import WebApp.Enterprise.Pollima.form.VoucherForm;
import WebApp.Enterprise.Pollima.model.Voucher;
import WebApp.Enterprise.Pollima.service.CargoService;
import WebApp.Enterprise.Pollima.service.TripService;
import WebApp.Enterprise.Pollima.service.VoucherService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("voucher")
@AllArgsConstructor
@Setter
public class VoucherController {

    private VoucherService voucherService;
    private TripService tripService;
    private CargoService cargoService;

    @GetMapping
    public String showVoucher(Model model){

        List<Voucher> voucherList = voucherService.findAll();
        model
                .addAttribute("allVouchers",voucherList)
                .addAttribute("addNewVoucher", VoucherForm.builder().build())
                .addAttribute("allTrip", tripService.findAll())
                .addAttribute("allCargo",cargoService.findAll());

        return "voucher";
    }

    @PostMapping
    private String saveVoucher(@ModelAttribute("saveNewVoucher") VoucherForm form) {
        tripService.findById(form.getTripId())
                .ifPresent(trip -> {
                    voucherService.save(
                            Voucher.builder()
                                    .id(form.getId())
                                    .trip(trip)
                                    .voucherNo(form.getVoucherNo())
                                    .cargo(trip.getCargo())
                                    .date(form.getDate())
                                    .dr(form.getDr())
                                    .cr(form.getCr())
                                    .particular(form.getParticular())
                                    .build()
                    );
                });

        return "redirect:/voucher";
    }
}
