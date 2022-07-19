package com.myfinance.myfinance.service.impl;

import com.myfinance.myfinance.domain.ScheduledTransaction;
import com.myfinance.myfinance.repository.ScheduledTransactionRepository;
import com.myfinance.myfinance.service.ScheduledTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledTransactionServiceImpl implements ScheduledTransactionService {

    @Autowired
    private ScheduledTransactionRepository scheduledTransactionRepository;

    @Override
    public List<ScheduledTransaction> getTransactionsByUserId(Integer userId) {
        return scheduledTransactionRepository.findAllByApplicationUser_Id(userId);
    }
}
