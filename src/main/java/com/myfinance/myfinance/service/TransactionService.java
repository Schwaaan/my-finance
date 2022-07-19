package com.myfinance.myfinance.service;

import com.myfinance.myfinance.domain.Transaction;
import com.myfinance.myfinance.domain.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    TransactionDTO create(TransactionDTO transaction);

    List<Transaction> getTransactionsByUserId(Integer userId);

    Transaction revertTransactionById(Integer transactionId);
}
