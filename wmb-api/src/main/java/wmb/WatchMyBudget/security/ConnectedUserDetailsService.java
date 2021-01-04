package wmb.WatchMyBudget.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wmb.WatchMyBudget.converter.UtilisateurConverter;
import wmb.WatchMyBudget.dtos.UtilisateurDTO;
import wmb.WatchMyBudget.models.Utilisateur;
import wmb.WatchMyBudget.repository.UtilisateurRepository;

import java.util.Collections;

@Service
public class ConnectedUserDetailsService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurConverter utilisateurConverter;

    @Autowired
    public ConnectedUserDetailsService(final UtilisateurRepository utilisateurRepository,
                                       final UtilisateurConverter utilisateurConverter) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurConverter = utilisateurConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String identifiant) throws UsernameNotFoundException {
        Utilisateur utilisateurCourant = utilisateurRepository.findByIdentifiant(identifiant);
        if (utilisateurCourant == null) {
            throw new UsernameNotFoundException(identifiant);
        }
        return new User(utilisateurCourant.getEmail(), utilisateurCourant.getMotDePasse(), Collections.emptyList());
    }

    public Utilisateur getUtilisateurCourant() {
        // On récupère l'identifiant de l'utilisateur connecté pour le retourner
        String identifiant = SecurityUtils.getUsername();
        return utilisateurRepository.findByIdentifiant(identifiant);
    }

    public UtilisateurDTO getUtilisateurCourantDTO(String identifiant, String tokenJwt) {
        Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(identifiant);
        return this.utilisateurConverter.toUtilisateurDTO(utilisateur, tokenJwt);
    }
}
