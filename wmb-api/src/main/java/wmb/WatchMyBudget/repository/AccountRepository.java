package wmb.WatchMyBudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wmb.WatchMyBudget.models.Account;
import wmb.WatchMyBudget.models.Utilisateur;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findAccountsByUtilisateur(Utilisateur utilisateur);
}
