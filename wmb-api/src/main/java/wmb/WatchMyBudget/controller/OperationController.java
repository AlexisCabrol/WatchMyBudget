package wmb.WatchMyBudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wmb.WatchMyBudget.dtos.AccountDTO;
import wmb.WatchMyBudget.dtos.OperationDTO;
import wmb.WatchMyBudget.service.OperationService;

@RestController
@RequestMapping("/")
public class OperationController {

    @Autowired
    public OperationService operationService;

    /**
     * Méthode permettant de créer en base le compte bancaire passée en paramètre.
     * @param identifiantCompte identifiant du compte à modifier.
     * @param operationDTO operation à ajouter.
     * @return l'entité créer.
     */
    @PostMapping("/comptes/{id}")
    public OperationDTO postCreerCompte(@PathVariable("id") Integer identifiantCompte, @RequestBody OperationDTO operationDTO) {
        return operationService.creerUneOperation(identifiantCompte, operationDTO);
    }
}
