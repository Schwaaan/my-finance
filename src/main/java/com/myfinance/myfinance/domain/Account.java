package com.myfinance.myfinance.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "O nome da conta deve ser preenchido")
    private String name;
    @NotNull(message = "O valor na conta não pode ser nulo")
    private BigDecimal amount;

    @OneToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    @NotNull(message = "O usuário dono da conta não pode ser nulo")
    private ApplicationUser applicationUser;

    public Account(String name, BigDecimal value, ApplicationUser applicationUser) {
        this.name = name;
        this.amount = value;
        this.applicationUser = applicationUser;
    }

    public Account() {

    }
}
