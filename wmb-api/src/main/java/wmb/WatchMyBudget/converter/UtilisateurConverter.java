package wmb.WatchMyBudget.converter;

import org.springframework.stereotype.Component;
import wmb.WatchMyBudget.dtos.UtilisateurDTO;
import wmb.WatchMyBudget.models.Utilisateur;

@Component
public class UtilisateurConverter {

    public UtilisateurDTO toUtilisateurDTO(final Utilisateur utilisateur, final String tokenJWT) {
        UtilisateurDTO utilisateurDTO = toUtilisateurDTO(utilisateur);
        utilisateurDTO.setTokenJWT(tokenJWT);
        return utilisateurDTO;
    }

    public UtilisateurDTO toUtilisateurDTO(final Utilisateur utilisateur) {
        return UtilisateurDTO.builder()
                .email(utilisateur.getEmail())
                .identifiant(utilisateur.getIdentifiant())
                .build();
    }
}
