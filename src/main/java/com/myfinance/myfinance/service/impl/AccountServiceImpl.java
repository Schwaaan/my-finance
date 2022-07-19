package com.myfinance.myfinance.service.impl;

import com.myfinance.myfinance.domain.Account;
import com.myfinance.myfinance.repository.AccountRepository;
import com.myfinance.myfinance.service.AccountService;
import com.myfinance.myfinance.utils.AssertUtil;
import com.myfinance.myfinance.utils.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account create(Account account) {
        Optional<Account> accountOptional = accountRepository.findByApplicationUser_Id(account.getApplicationUser().getId());

        if(accountOptional.isPresent()){
            throw new BadRequestException("Usuário já possui uma conta");
        }

        return accountRepository.save(account);
    }
    @Override
    public Account findAccountByUserId(Integer id) {
        return AssertUtil.found(accountRepository.findByApplicationUser_Id(id), "Não existe nenhuma conta para este usuário");
    }
}
