package WebApp.Enterprise.Pollima.service;

import WebApp.Enterprise.Pollima.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VoucherService {

    List<Voucher> findAll();
    Optional<Voucher> findById(Long id);
    void save(Voucher voucher);
    Page<Voucher> findAll(Pageable pageable);
    void delete(Long id);
}
