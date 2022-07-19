package com.myfinance.myfinance.controllers;

import com.myfinance.myfinance.domain.Account;
import com.myfinance.myfinance.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    private Account create(@RequestBody @Valid Account account) {
        return accountService.create(account);
    }

    @GetMapping(path = "/{userId}")
    private Account getAccountByUserId(@PathVariable(value = "userId") Integer id) {
        return accountService.findAccountByUserId(id);
    }

}
