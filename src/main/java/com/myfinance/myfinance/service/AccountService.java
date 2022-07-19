package com.myfinance.myfinance.service;

import com.myfinance.myfinance.domain.Account;

public interface AccountService {
    Account create(Account account);

    Account findAccountByUserId(Integer id);

}
