package wmb.WatchMyBudget.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wmb.WatchMyBudget.converter.AccountConverter;
import wmb.WatchMyBudget.dtos.AccountDTO;
import wmb.WatchMyBudget.models.Account;
import wmb.WatchMyBudget.repository.AccountRepository;
import wmb.WatchMyBudget.security.ConnectedUserDetailsService;

import java.util.List;

@Service
public class AccountService {

    private final ConnectedUserDetailsService connectedUserDetailsService;
    private final AccountRepository accountRepository;
    private final AccountConverter accountConverter;

    @Autowired
    public AccountService(ConnectedUserDetailsService connectedUserDetailsService,
                          AccountRepository accountRepository,
                          AccountConverter accountConverter) {
        this.connectedUserDetailsService = connectedUserDetailsService;
        this.accountRepository = accountRepository;
        this.accountConverter = accountConverter;
    }

    /**
     * Méthode permettant de récupérer les comptes bancaires par utilisateur.
     * @return la liste des comptes par utilisateur.
     */
    public List<AccountDTO> getCompteParUtilisateur() {
        final List<Account> accounts = accountRepository
                .findAccountsByUtilisateur(connectedUserDetailsService.getUtilisateurCourant());
        return accountConverter.toAccountsDTOs(accounts);
    }

    /**
     * Méthode permettant la création d'un compte bancaire en base.
     * @param accountDTO information compte bancaire issue du front.
     * @return l'entité créée en base.
     */
    public AccountDTO creerCompte(AccountDTO accountDTO) {
        Account account = accountConverter.toAccountEntity(accountDTO);
        account.setUtilisateur(connectedUserDetailsService.getUtilisateurCourant());
        account = accountRepository.save(account);
        return accountConverter.toAccountDTO(account);
     }
}
