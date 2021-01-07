package wmb.WatchMyBudget.dtos;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class UtilisateurDTO implements Serializable {
    private String email;
    private String identifiant;
    private String motDePasse;
    private String tokenJWT;
}
