package com.myfinance.myfinance.service;

import com.myfinance.myfinance.domain.ScheduledTransaction;
import com.myfinance.myfinance.domain.Transaction;

import java.util.List;

public interface ScheduledTransactionService {

    List<ScheduledTransaction> getTransactionsByUserId(Integer userId);
}
