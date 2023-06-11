package WebApp.Enterprise.Pollima.model;

import lombok.*;

import javax.persistence.*;
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

    @OneToMany(targetEntity = Trip.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "cargoId", referencedColumnName = "ID")
    private List<Trip> tripList;


}
