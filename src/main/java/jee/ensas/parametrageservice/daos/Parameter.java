package jee.ensas.parametrageservice.daos;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;

@Document("parameters")
@Data
public class Parameter implements Serializable {

    @Id
    String id;

    long maxAmountForCashToCash;
    long maxAmountForCashToAccount;
    long maxAmountForAccountToCash;
    long maxAmountForAccountToAccount;

    int maxHoldingTimeForCashToCash;
    int maxHoldingTimeForCashToAccount;
    int maxHoldingTimeForAccountToCash;
    int maxHoldingTimeForAccountToAccount;

    double transferPercentageForCashToCash;
    double transferPercentageForCashToAccount;
    double transferPercentageForAccountToAccount;
    double transferPercentageForAccountToCash;

}
