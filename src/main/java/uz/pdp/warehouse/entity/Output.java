package uz.pdp.warehouse.entity;

import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Output {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Timestamp date;

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Currency currency;

    private String factureNumber;

    @Column(nullable = false,unique = true)
    private String code;

    @ManyToOne
    private Client client;
}
