package com.myfinance.myfinance.domain.dto;

import com.myfinance.myfinance.domain.Account;
import com.myfinance.myfinance.domain.ApplicationUser;
import com.myfinance.myfinance.domain.enums.TransactionType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class TransactionDTO {

    @NotNull(message = "A Conta destino deve ser preenchida")
    private Integer toAccountId;
    @NotNull(message = "A Conta de origem deve ser preenchida")
    private Integer fromAccountId;
    @NotNull(message = "O valor para transferir deve ser preenchido")
    private BigDecimal amount;
    @NotNull(message = "O tipo da transação deve ser preenchido")
    private Integer type;

    private Integer parcels;

    @NotNull(message = "O usuário de criação da transação deve ser informado")
    private Integer createdById;

    public TransactionDTO(Integer toAccount, Integer fromAccount, BigDecimal bigDecimal, Integer applicationUser) {
        this.toAccountId = toAccount;
        this.fromAccountId = fromAccount;
        this.amount = bigDecimal;
        this.createdById = applicationUser;
    }

    public TransactionType getType() {
        return TransactionType.getEnum(this.type);
    }

}
