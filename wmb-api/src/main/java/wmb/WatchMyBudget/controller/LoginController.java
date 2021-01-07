package wmb.WatchMyBudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wmb.WatchMyBudget.dtos.UtilisateurDTO;
import wmb.WatchMyBudget.models.Utilisateur;
import wmb.WatchMyBudget.service.UtilisateurService;

@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    public UtilisateurService utilisateurService;

    /**
     * Méthode permettant d'enregistrer un nouvel utilisateur.
     * @param utilisateurDTO
     */
    @PostMapping("/register")
    public void inscription(@RequestBody UtilisateurDTO utilisateurDTO) {
        utilisateurService.enregistrerUnUtilisateur(utilisateurDTO);
    }
}
