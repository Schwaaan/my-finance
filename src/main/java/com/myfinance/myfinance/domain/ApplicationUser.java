package com.myfinance.myfinance.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class ApplicationUser {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "O nome do usuário deve ser preenchido")
    private String name;
    @NotEmpty(message = "A senha do usuário deve ser preenchida")
    private String password;

    public ApplicationUser(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
