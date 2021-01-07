package wmb.WatchMyBudget.dtos;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class AccountDTO implements Serializable {

    private Integer id;
    private String libelle;
    private BigDecimal solde;
    private List<OperationDTO> operations;
}
