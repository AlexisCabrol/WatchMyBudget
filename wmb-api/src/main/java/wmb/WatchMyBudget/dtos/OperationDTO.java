package wmb.WatchMyBudget.dtos;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class OperationDTO implements Serializable {

    private Integer id;
    private String libelle;
    private BigDecimal solde;
}
