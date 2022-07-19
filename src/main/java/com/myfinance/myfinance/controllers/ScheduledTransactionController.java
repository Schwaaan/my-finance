package com.myfinance.myfinance.controllers;

import com.myfinance.myfinance.domain.ScheduledTransaction;
import com.myfinance.myfinance.domain.Transaction;
import com.myfinance.myfinance.domain.dto.TransactionDTO;
import com.myfinance.myfinance.service.ScheduledTransactionService;
import com.myfinance.myfinance.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
