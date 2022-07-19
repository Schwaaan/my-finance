package com.myfinance.myfinance.domain;

import com.myfinance.myfinance.domain.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@Table
public class Transaction {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Account toAccount;
    @OneToOne
    private Account fromAccount;


    private Integer status;

    private BigDecimal amount;

    @OneToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private ApplicationUser applicationUser;


    public TransactionStatus getStatus() {
        return TransactionStatus.getEnum(this.status);
    }

    public Transaction(Account toAccount, Account fromAccount, BigDecimal amount, ApplicationUser applicationUser, Integer status) {
        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.amount = amount;
        this.applicationUser = applicationUser;
        this.status = status;
    }

    public Transaction() {
    }

}
