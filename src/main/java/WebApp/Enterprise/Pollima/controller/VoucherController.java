package WebApp.Enterprise.Pollima.controller;

import WebApp.Enterprise.Pollima.form.VoucherForm;
import WebApp.Enterprise.Pollima.model.Trip;
import WebApp.Enterprise.Pollima.model.Voucher;
import WebApp.Enterprise.Pollima.service.CargoService;
import WebApp.Enterprise.Pollima.service.TripService;
import WebApp.Enterprise.Pollima.service.VoucherService;
import com.sun.jdi.DoubleValue;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/voucher")
@AllArgsConstructor
@Setter
public class VoucherController {

    private VoucherService voucherService;
    private TripService tripService;
    private CargoService cargoService;

    private MessageSource messageSource;

    @GetMapping
    public String showVoucher(Model model,@PageableDefault(size = 2) Pageable pageable){

        Page<Voucher> voucherPage = voucherService.findAll(pageable);
        List<Voucher> voucherList = voucherPage.getContent();
        List<Trip> latestTrips= tripService.findLatestTrip(5);
        model
                .addAttribute("allVouchers",voucherList)
                .addAttribute("addNewVoucher", VoucherForm.builder().build())
                .addAttribute("allTrip", tripService.findAll())
                .addAttribute("allCargo",cargoService.findAll())
                .addAttribute("voucherPage",voucherPage)
                .addAttribute("latestTrips",latestTrips);

        return "voucher";
    }

    @PostMapping
    private String saveVoucher(@Valid @ModelAttribute("addNewVoucher") VoucherForm form, BindingResult result, Model model, @PageableDefault(size = 2) Pageable pageable) {
        String message = messageSource.getMessage("cr.or.dr.required", null, Locale.getDefault());

        if (form.getCr() == null && form.getDr() == null){
                result.rejectValue("cr","cr.or.dr.required", message);
                result.rejectValue("dr","cr.or.dr.required", message);
            } else if (form.getCr() == null) {
            form.setCr(0.00);
        }

        if (result.hasErrors()){
            List<Trip> latestTrips= tripService.findLatestTrip(5);
            Page<Voucher> voucherPage = voucherService.findAll(pageable);
            List<Voucher> voucherList = voucherPage.getContent();
            model
                    .addAttribute("allVouchers",voucherList)
                    .addAttribute("allTrip",tripService.findAll())
                    .addAttribute("voucherPage",voucherPage)
                    .addAttribute("latestTrips",latestTrips);
            return "/voucher";
        }

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
    @PostMapping("/delete")
    public String deleteVoucher(@RequestParam(name = "id")long id){
        voucherService.delete(id);
        return "redirect:/voucher";
    }

    @GetMapping("/edit/{id}")
    public String editVoucher(Model model, @PathVariable(name = "id") long id){
        List<Trip> latestTrips= tripService.findLatestTrip(5);
        voucherService.findById(id).ifPresent(voucher -> {
            VoucherForm voucherForm = VoucherForm.builder()
                    .id(voucher.getId())
                    .tripId(voucher.getTrip().getId())
                    .voucherNo(voucher.getVoucherNo())
                    .date(voucher.getDate())
                    .dr(voucher.getDr())
                    .cr(voucher.getCr())
                    .particular(voucher.getParticular())
                    .build();
            model
                    .addAttribute("addNewVoucher", VoucherForm.builder().build())
                    .addAttribute("allTrip", tripService.findAll())
                    .addAttribute("allCargo",cargoService.findAll())
                    .addAttribute("latestTrips",latestTrips);
        });

        return "voucher";
    }
}
