package com.myfinance.myfinance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@Table
public class ScheduledTransaction {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Account toAccount;
    @OneToOne
    private Account fromAccount;

    private Integer parcels;

    private BigDecimal amountPayable;

    @OneToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private ApplicationUser applicationUser;

    public ScheduledTransaction(Account toAccount, Account fromAccount, Integer parcels, BigDecimal amountPayable, ApplicationUser applicationUser) {
        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.parcels = parcels;
        this.amountPayable = amountPayable;
        this.applicationUser = applicationUser;
    }

    public ScheduledTransaction() {

    }
}
