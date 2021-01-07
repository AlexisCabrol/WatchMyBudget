package wmb.WatchMyBudget.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import wmb.WatchMyBudget.converter.UtilisateurConverter;
import wmb.WatchMyBudget.dtos.UtilisateurDTO;
import wmb.WatchMyBudget.models.Utilisateur;
import wmb.WatchMyBudget.repository.UtilisateurRepository;
import wmb.WatchMyBudget.security.ConnectedUserDetailsService;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UtilisateurConverter utilisateurConverter;

    @Autowired
    public UtilisateurService(final UtilisateurRepository utilisateurRepository,
                              final BCryptPasswordEncoder bCryptPasswordEncoder,
                              final UtilisateurConverter utilisateurConverter) {
        this.utilisateurRepository = utilisateurRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.utilisateurConverter = utilisateurConverter;
    }

    /**
     * Méthode permettant d'enregistrer un utilisateur en base de données.
     * @param utilisateurDTO l'utilisateur à inscrire en base.
     */
    public void enregistrerUnUtilisateur(final UtilisateurDTO utilisateurDTO) {
        final Utilisateur utilisateur = utilisateurConverter.toUtilisateurEntity(utilisateurDTO);
        utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateurDTO.getMotDePasse()));
        utilisateurRepository.save(utilisateur);
    }

}
