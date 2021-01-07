package wmb.WatchMyBudget.converter;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import wmb.WatchMyBudget.dtos.AccountDTO;
import wmb.WatchMyBudget.models.Account;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountConverter {

    private final OperationConverter operationConverter;

    public AccountConverter(final OperationConverter operationConverter) {
        this.operationConverter = operationConverter;
    }

    /**
     * Méthode permettant de transformer un account entity en account DTO.
     * @param account account entity.
     * @return account DTO.
     */
    public AccountDTO toAccountDTO(final Account account) {
        if(account == null) {
            return null;
        }

        return AccountDTO.builder()
                .id(account.getId())
                .libelle(account.getLibelle())
                .solde(account.getSolde())
                .operations(operationConverter.toOperationsDTOs(account.getOperations()))
                .build();
    }

    /**
     * Méthode permettant de transformer une liste de compte entity en liste de compte DTO.
     * @param accounts liste de compte entity.
     * @return liste de compte en DTO.
     */
    public List<AccountDTO> toAccountsDTOs(final List<Account> accounts) {
        if(accounts == null) {
            return null;
        }
        return accounts.parallelStream()
                .map(this::toAccountDTO)
                .collect(Collectors.toList());
    }

    /**
     * Méthode permettant de créer un account entity à partir d'un account DTO.
     * @param accountDTO account DTO issue du front.
     * @return un account entity.
     */
    public Account toAccountEntity(final AccountDTO accountDTO) {
        if(accountDTO == null) {
            return null;
        }
        Account account = Account.builder()
                .libelle(accountDTO.getLibelle())
                .solde(accountDTO.getSolde())
                .build();

        if(!CollectionUtils.isEmpty(accountDTO.getOperations())) {
            // TODO
        }
        return account;
    }
}
