package WebApp.Enterprise.Pollima.repository;

import WebApp.Enterprise.Pollima.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyName(String name);

    Page<Company> findAll(Pageable pageable);
}
