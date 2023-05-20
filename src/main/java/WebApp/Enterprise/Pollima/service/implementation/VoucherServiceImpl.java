package WebApp.Enterprise.Pollima.service.implementation;

import WebApp.Enterprise.Pollima.model.Voucher;
import WebApp.Enterprise.Pollima.repository.VoucherRepository;
import WebApp.Enterprise.Pollima.service.VoucherService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Setter
public class VoucherServiceImpl implements VoucherService {

    private VoucherRepository voucherRepository;

    @Override
    public List<Voucher> findAll() {
        return voucherRepository.findAll();
    }

    @Override
    public Optional<Voucher> findById(Long id) {
        return voucherRepository.findById(id);
    }

    @Override
    public void save(Voucher voucher) {
        voucherRepository.save(voucher);
    }
}
