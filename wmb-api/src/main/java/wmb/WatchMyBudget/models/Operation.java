package wmb.WatchMyBudget.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "OPERATION")
public class Operation implements Serializable {

    @Id
    @SequenceGenerator(name = "SEQ_OPERATION", sequenceName = "SEQ_OPERATION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OPERATION")
    @Column(name = "ID_OPERATION")
    private Integer id;

    @Column(name = "LIBELLE")
    private String libelle;

    @Column(name = "SOLDE")
    private BigDecimal solde;

    @ManyToOne
    @JoinColumn(name="ID_ACCOUNT", nullable = false)
    private Account account;
}
