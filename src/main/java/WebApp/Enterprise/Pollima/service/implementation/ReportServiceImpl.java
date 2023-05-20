//package WebApp.Enterprise.Pollima.service.implementation;
//
//import WebApp.Enterprise.Pollima.model.Report;
//import WebApp.Enterprise.Pollima.repository.ReportRepository;
//import WebApp.Enterprise.Pollima.service.ReportService;
//import lombok.AllArgsConstructor;
//import lombok.Setter;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Setter
//@AllArgsConstructor
//public class ReportServiceImpl implements ReportService {
//
//    private ReportRepository reportRepository;
//
//    @Override
//    public List<Report> findAll() {
//        return reportRepository.findAll();
//    }
//
//    @Override
//    public void save(Report report) {
//
//        reportRepository.save(report);
//    }
//
//    @Override
//    public Optional<Report> findById(Long id) {
//        return reportRepository.findById(id);
//    }
//
//}
