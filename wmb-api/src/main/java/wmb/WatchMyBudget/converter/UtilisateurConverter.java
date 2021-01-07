package wmb.WatchMyBudget.converter;

import org.springframework.stereotype.Component;
import wmb.WatchMyBudget.dtos.UtilisateurDTO;
import wmb.WatchMyBudget.models.Utilisateur;

@Component
public class UtilisateurConverter {

    /**
     * Méthode permettant de transformer un utilisateur en utilisateur DTO.
     * @param utilisateur utilisateur.
     * @param tokenJWT token JWT.
     * @return utilisateur DTO.
     */
    public UtilisateurDTO toUtilisateurDTO(final Utilisateur utilisateur, final String tokenJWT) {
        UtilisateurDTO utilisateurDTO = toUtilisateurDTO(utilisateur);
        utilisateurDTO.setTokenJWT(tokenJWT);
        return utilisateurDTO;
    }

    /**
     * Méthode permettant de transformer un utilisateur en utilisateur DTO.
     * @param utilisateur utilisateur.
     * @return utilisateur DTO.
     */
    public UtilisateurDTO toUtilisateurDTO(final Utilisateur utilisateur) {
        return UtilisateurDTO.builder()
                .identifiant(utilisateur.getIdentifiant())
                .build();
    }

    /**
     * Méthode permettant de transformer un utilisateur DTO en utilisateur entity.
     * @param utilisateurDTO utilisateur DTO.
     * @return utilisateur entity.
     */
    public Utilisateur toUtilisateurEntity(final UtilisateurDTO utilisateurDTO) {
        return Utilisateur.builder()
                .identifiant(utilisateurDTO.getIdentifiant())
                .email(utilisateurDTO.getEmail())
                .build();
    }
}
