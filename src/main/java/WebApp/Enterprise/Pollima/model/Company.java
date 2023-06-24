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
    private Long id;

    @Column
    private String companyName;

    @Column
    private String contactPerson;

    @Column
    private String office;

    @Column
    private String contactNo;

    @OneToMany(targetEntity = Cargo.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_Id", referencedColumnName = "id")
    private List<Cargo> CargoList;

    @OneToMany(targetEntity = Trip.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId", referencedColumnName = "id")
    private List<Trip> tripList;

}
