package wmb.WatchMyBudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wmb.WatchMyBudget.models.Account;
import wmb.WatchMyBudget.models.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {
}
