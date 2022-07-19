package com.myfinance.myfinance;

import com.myfinance.myfinance.domain.Account;
import com.myfinance.myfinance.domain.ApplicationUser;
import com.myfinance.myfinance.domain.ScheduledTransaction;
import com.myfinance.myfinance.domain.Transaction;
import com.myfinance.myfinance.domain.dto.TransactionDTO;
import com.myfinance.myfinance.domain.enums.TransactionStatus;
import com.myfinance.myfinance.service.AccountService;
import com.myfinance.myfinance.service.ApplicationUserService;
import com.myfinance.myfinance.service.ScheduledTransactionService;
import com.myfinance.myfinance.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ScheduledTransactionService scheduledTransactionService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ApplicationUserService applicationUserService;

    private Account fromAccount;
    private Account toAccount;
    private ApplicationUser applicationUser;
    private ApplicationUser applicationUser2;


    @BeforeEach
    void init() {
        applicationUser = applicationUserService.create(new ApplicationUser("teste", "123"));
        applicationUser2 = applicationUserService.create(new ApplicationUser("teste", "123"));
        fromAccount = accountService.create(new Account("JairAccount", new BigDecimal(100), applicationUser));
        toAccount = accountService.create(new Account("JairAccount2", new BigDecimal(100), applicationUser2));
    }

    @Test
    void pixTransactionTest() {
        TransactionDTO transactionDTO = new TransactionDTO(toAccount.getId(), fromAccount.getId(), new BigDecimal(10), applicationUser.getId());

        transactionService.create(transactionDTO);

        List<Transaction> transaction1 = transactionService.getTransactionsByUserId(applicationUser.getId());
        assertEquals(new BigDecimal(90).setScale(2), transaction1.get(0).getFromAccount().getAmount());
    }

    @Test
    void scheduledTransactionTest() {
        TransactionDTO transactionDTO = new TransactionDTO(toAccount.getId(), fromAccount.getId(), new BigDecimal(50), applicationUser.getId());
        transactionDTO.setType(2);
        transactionDTO.setParcels(2);

        transactionService.create(transactionDTO);

        List<ScheduledTransaction> scheduledTransactions = scheduledTransactionService.getTransactionsByUserId(applicationUser.getId());

        assertFalse(scheduledTransactions.isEmpty());
        assertEquals(2, scheduledTransactions.size());
        assertEquals(new BigDecimal(25).setScale(2), scheduledTransactions.get(0).getAmountPayable());
    }

    @Test
    void revertTransactionByIdTest() {
        TransactionDTO transactionDTO = new TransactionDTO(toAccount.getId(), fromAccount.getId(), new BigDecimal(50), applicationUser.getId());

        transactionService.create(transactionDTO);

        List<Transaction> transactions = transactionService.getTransactionsByUserId(applicationUser.getId());

        Transaction transaction = transactionService.revertTransactionById(transactions.get(0).getId());
        Account account = accountService.findAccountByUserId(transaction.getToAccount().getApplicationUser().getId());


        assertEquals(TransactionStatus.REVERT, transaction.getStatus());
        assertEquals(new BigDecimal(100).setScale(2), account.getAmount());
    }
}
