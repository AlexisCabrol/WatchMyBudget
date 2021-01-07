package wmb.WatchMyBudget.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import wmb.WatchMyBudget.converter.OperationConverter;
import wmb.WatchMyBudget.dtos.OperationDTO;
import wmb.WatchMyBudget.models.Account;
import wmb.WatchMyBudget.models.Operation;
import wmb.WatchMyBudget.models.Utilisateur;
import wmb.WatchMyBudget.repository.AccountRepository;
import wmb.WatchMyBudget.repository.OperationRepository;
import wmb.WatchMyBudget.security.ConnectedUserDetailsService;

import java.util.Optional;

@Service
public class OperationService {

    private final ConnectedUserDetailsService connectedUserDetailsService;
    private final OperationConverter operationConverter;
    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;

    public OperationService(final ConnectedUserDetailsService connectedUserDetailsService,
                            final OperationConverter operationConverter,
                            final AccountRepository accountRepository,
                            final OperationRepository operationRepository) {
        this.connectedUserDetailsService = connectedUserDetailsService;
        this.operationConverter = operationConverter;
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    /**
     * Méthode permettant de créer une opération sur un compte en banque.
     * @param identifiantCompte identifiant du compte à modifier.
     * @param operationDTO opération à ajouter.
     * @return l'opération persisté en base.
     */
    public OperationDTO creerUneOperation(Integer identifiantCompte, OperationDTO operationDTO) {
        Operation operation = operationConverter.toOperationEntity(operationDTO);

        // On charge le compte bancaire auquel on doit ajouter l'opération.
        Optional<Account> account = accountRepository.findById(identifiantCompte);

        // Si le compte bancaire existe en base : on continue le traitement.
        // Sinon on rejette la requête.
        if(account.isPresent()) {
            Utilisateur titulaireCompte = account.get().getUtilisateur();
            Utilisateur utilisateurCourant = connectedUserDetailsService.getUtilisateurCourant();

            // Si l'identifiant du titulaire et le même que l'utilisateur courant alors l'utilisateur a accès à la
            // modification de l'entité.
            // Sinon, c'est qu'il n'est pas titulaire du compte qu'il essaye de modifier. On rejette la requête.
            if(titulaireCompte.getId().equals(utilisateurCourant.getId())) {
                operation.setAccount(account.get());
                operation = operationRepository.save(operation);

                account.get().setSolde(account.get().getSolde().add(operation.getSolde()));
                accountRepository.save(account.get());
                return operationConverter.toOperationDTO(operation);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
