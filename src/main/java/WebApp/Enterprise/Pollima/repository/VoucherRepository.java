package WebApp.Enterprise.Pollima.repository;

import WebApp.Enterprise.Pollima.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {

}
