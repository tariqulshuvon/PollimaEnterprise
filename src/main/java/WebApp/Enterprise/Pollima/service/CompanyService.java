package WebApp.Enterprise.Pollima.service;

import WebApp.Enterprise.Pollima.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> findAll();

    void save(Company company);

    Optional<Company> findById(Long id);

    void delete(Company company);

    Optional<Company> findByCompanyName(String name);
    Page<Company> findAll(Pageable pageable);

    Page<Company> findAll(Pageable pageable);

}
