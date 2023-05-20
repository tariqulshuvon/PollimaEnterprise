package WebApp.Enterprise.Pollima.service;

import WebApp.Enterprise.Pollima.model.Voucher;

import java.util.List;
import java.util.Optional;

public interface VoucherService {

    List<Voucher> findAll();
    Optional<Voucher> findById(Long id);
    void save(Voucher voucher);

}
