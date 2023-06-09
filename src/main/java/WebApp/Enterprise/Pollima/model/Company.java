package WebApp.Enterprise.Pollima.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column
    private String CompanyName;

    @Column
    private String contactPerson;

    @Column
    private String office;

    @Column
    private String contactNo;

    @OneToMany(targetEntity = Cargo.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "Company_ID", referencedColumnName = "ID")
    private List<Cargo> CargoList;

    @OneToMany(targetEntity = Trip.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId", referencedColumnName = "ID")
    private List<Trip> tripList;

}
