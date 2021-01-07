package wmb.WatchMyBudget.converter;

import org.springframework.stereotype.Component;
import wmb.WatchMyBudget.dtos.OperationDTO;
import wmb.WatchMyBudget.models.Operation;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OperationConverter {

    /**
     * Méthode permettant de transformer une opération entity en opération DTO.
     * @param operation opération entity.
     * @return opération DTO.
     */
    public OperationDTO toOperationDTO(final Operation operation) {
        return OperationDTO.builder()
                .id(operation.getId())
                .libelle(operation.getLibelle())
                .solde(operation.getSolde())
                .build();
    }

    /**
     * Méthode permettant de transformer une liste opérations entities en liste opérations DTOs.
     * @param operations liste opérations entities.
     * @return liste opérations DTOs.
     */
    public List<OperationDTO> toOperationsDTOs(final List<Operation> operations) {
        if(operations == null) {
            return null;
        }

        return operations.parallelStream()
                .map(this::toOperationDTO)
                .collect(Collectors.toList());
    }

    /**
     * Méthode permettant de transformer une opération DTO en opération entity.
     * @param operationDTO opération DTO.
     * @return opération entity.
     */
    public Operation toOperationEntity(final OperationDTO operationDTO) {
        if(operationDTO == null) {
            return null;
        }

        return Operation.builder()
                .libelle(operationDTO.getLibelle())
                .solde(operationDTO.getSolde())
                .build();
    }
}
