package WebApp.Enterprise.Pollima.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trip")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "ID")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "cargoId", referencedColumnName = "ID")
    private Cargo cargo;

    @Column
    private String  startDate;

    @Column
    private String endDate;

    @Column
    private String fromLocation;

    @Column
    private String toLocation;

    @Column
    private Double rent;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<Voucher> vouchers;


}
