package WebApp.Enterprise.Pollima.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "voucher")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Trip trip;

    @Column(name = "voucher_no")
    private String voucherNo;

    @ManyToOne
    @JoinColumn(name = "cargo_id", referencedColumnName = "ID")
    private Cargo cargo;

    @Column(name = "date")
    private String date;

    @Column(name = "dr")
    private double dr;

    @Column(name = "cr")
    private double cr;

    @Column(name = "particular")
    private String particular;
}
