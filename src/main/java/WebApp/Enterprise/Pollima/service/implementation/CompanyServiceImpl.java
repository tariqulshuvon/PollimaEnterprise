package WebApp.Enterprise.Pollima.service.implementation;

import WebApp.Enterprise.Pollima.model.Company;
import WebApp.Enterprise.Pollima.repository.CompanyRepository;
import WebApp.Enterprise.Pollima.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public void save(Company company) {

        companyRepository.save(company);

    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public void delete(Company company) {
        companyRepository.delete(company);
    }

    @Override
    public Optional<Company> findByCompanyName(String name) {
        return companyRepository.findByCompanyName(name);
    }

    @Override
    public Page<Company> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }
}
