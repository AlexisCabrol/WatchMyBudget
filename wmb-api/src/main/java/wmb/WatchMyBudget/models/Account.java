package wmb.WatchMyBudget.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ACCOUNT")
public class Account implements Serializable {

    @Id
    @SequenceGenerator(name = "SEQ_ACCOUNT", sequenceName = "SEQ_ACCOUNT", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACCOUNT")
    @Column(name = "ID_ACCOUNT")
    private Integer id;

    @Column(name = "LIBELLE")
    private String libelle;

    @Column(name = "SOLDE")
    private BigDecimal solde;

    @OneToMany(mappedBy = "account")
    private List<Operation> operations;

    @ManyToOne
    @JoinColumn(name="ID_UTILISATEUR", nullable = false)
    private Utilisateur utilisateur;
}
