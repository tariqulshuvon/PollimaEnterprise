package WebApp.Enterprise.Pollima.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cargo")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column
    private String cargoName;

    @Column
    private String Proprietor;

    @Column
    private String Contact;

    @Column
    private String Address;

    @Column
    private String Reference;

    @Column
    private String ContactStartDate;
//
//    @ManyToOne
//    @JoinColumn(name = "Company_ID", referencedColumnName = "ID")
//    private Company company;

    @OneToMany(targetEntity = Trip.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "cargoId", referencedColumnName = "ID")
    private List<Trip> tripList;


}
