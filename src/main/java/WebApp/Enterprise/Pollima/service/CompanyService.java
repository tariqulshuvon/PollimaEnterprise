package WebApp.Enterprise.Pollima.service;

import WebApp.Enterprise.Pollima.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> findAll();

    void save(Company company);

    Optional<Company> findById(Long id);

    void delete(Long id);

    Optional<Company> findByCompanyName(String name);

}
