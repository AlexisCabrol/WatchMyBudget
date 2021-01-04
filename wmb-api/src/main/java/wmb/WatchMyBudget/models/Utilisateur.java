package wmb.WatchMyBudget.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "UTILISATEUR")
public class Utilisateur implements Serializable {

    @Id
    @SequenceGenerator(name = "SEQ_UTILISATEUR", sequenceName = "SEQ_UTILISATEUR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_UTILISATEUR")
    @Column(name = "ID_UTILISATEUR")
    private Integer id;

    @Column(name = "IDENTIFIANT")
    private String identifiant;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "MOTDEPASSE")
    private String motDePasse;

    @OneToMany(mappedBy = "utilisateur")
    private List<Account> accounts;
}
