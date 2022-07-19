package com.myfinance.myfinance.controllers;

import com.myfinance.myfinance.domain.Transaction;
import com.myfinance.myfinance.domain.dto.TransactionDTO;
import com.myfinance.myfinance.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public TransactionDTO create(@RequestBody @Valid TransactionDTO transactionDTO) {
        return transactionService.create(transactionDTO);
    }

    @GetMapping(path = "/{userId}")
    private List<Transaction> getTransactionsByUserId(@PathVariable(value = "userId") Integer userId) {
        return transactionService.getTransactionsByUserId(userId);
    }

    @PutMapping(path = "/revert/{transactionId}")
    private Transaction revertTransactionById(@PathVariable(value = "transactionId") Integer transactionId) {
        return transactionService.revertTransactionById(transactionId);
    }
}
