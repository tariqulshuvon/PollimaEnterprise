//package WebApp.Enterprise.Pollima.model;
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.List;
//
//
//@Entity
//@Table(name = "report")
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Report {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToMany
//    @JoinColumn(name = "report_id")
//    private List<Trip> tripList;
//
//    @Column
//    private Double totalRent;
//
//    @Column
//    private Double dr;
//
//    @Column
//    private Double cr;
//
//    @Column
//    private Double balance;
//
//
//}
