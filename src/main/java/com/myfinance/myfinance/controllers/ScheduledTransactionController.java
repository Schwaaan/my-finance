package com.myfinance.myfinance.controllers;

import com.myfinance.myfinance.domain.ScheduledTransaction;
import com.myfinance.myfinance.service.ScheduledTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/scheduled-transactions")
public class ScheduledTransactionController {

    @Autowired
    private ScheduledTransactionService scheduledTransactionService;

    @GetMapping(path = "/{userId}")
    private List<ScheduledTransaction> getTransactionsByUserId(@PathVariable(value = "userId") Integer userId) {
        return scheduledTransactionService.getTransactionsByUserId(userId);
    }

}
