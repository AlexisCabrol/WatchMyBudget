package wmb.WatchMyBudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wmb.WatchMyBudget.dtos.AccountDTO;
import wmb.WatchMyBudget.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Méthode permettant de récupérer les comptes bancaires par utilisateur.
     * @return la liste des comptes par utilisateur.
     */
    @GetMapping("/comptes")
    public List<AccountDTO> getCompteParUtilisateur() {
        return accountService.getCompteParUtilisateur();
    }

    /**
     * Méthode permettant de créer en base le compte bancaire passée en paramètre.
     * @return l'entité créer.
     */
    @PostMapping("/comptes")
    public AccountDTO postCreerCompte(@RequestBody AccountDTO accountDTO) {
        return accountService.creerCompte(accountDTO);
    }
}
