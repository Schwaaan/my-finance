package com.myfinance.myfinance.service.impl;

import com.myfinance.myfinance.domain.Account;
import com.myfinance.myfinance.domain.ApplicationUser;
import com.myfinance.myfinance.domain.ScheduledTransaction;
import com.myfinance.myfinance.domain.Transaction;
import com.myfinance.myfinance.domain.dto.TransactionDTO;
import com.myfinance.myfinance.domain.enums.TransactionStatus;
import com.myfinance.myfinance.domain.enums.TransactionType;
import com.myfinance.myfinance.repository.AccountRepository;
import com.myfinance.myfinance.repository.ApplicationUserRepository;
import com.myfinance.myfinance.repository.ScheduledTransactionRepository;
import com.myfinance.myfinance.repository.TransactionRepository;
import com.myfinance.myfinance.service.TransactionService;
import com.myfinance.myfinance.utils.AssertUtil;
import com.myfinance.myfinance.utils.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ScheduledTransactionRepository scheduleTransactionRepository;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public TransactionDTO create(TransactionDTO transaction) {
        Account fromAccount = AssertUtil.found(accountRepository.findById(transaction.getFromAccountId()), "Conta de origem não encontrada");
        Account toAccount = AssertUtil.found(accountRepository.findById(transaction.getToAccountId()), "Conta de destino não encontrada");
        ApplicationUser applicationUser = AssertUtil.found(applicationUserRepository.findById(transaction.getCreatedById()), "Usuário de criação não encontrado");

        if (Objects.equals(transaction.getType(), TransactionType.PIX)) {
            return pixTransaction(transaction, toAccount, fromAccount, applicationUser);
        }

        return scheduledTransaction(transaction, toAccount, fromAccount, applicationUser);
    }

    @Override
    public List<Transaction> getTransactionsByUserId(Integer userId) {
        return transactionRepository.findAllByApplicationUser_Id(userId);
    }

    @Override
    public Transaction revertTransactionById(Integer transactionId) {
        Transaction transaction = AssertUtil.found(transactionRepository.findById(transactionId), "Transação não encontrada");

        Account fromAccount = AssertUtil.found(accountRepository.findById(transaction.getFromAccount().getId()), "Conta de origem não encontrada");
        Account toAccount = AssertUtil.found(accountRepository.findById(transaction.getToAccount().getId()), "Conta de destino não encontrada");

        if (toAccount.getAmount().compareTo(transaction.getAmount()) < 0) {
            throw new BadRequestException("A transação não pode ser revertida pois o valor é maior do que o valor existente na conta de destino");
        }

        toAccount.setAmount(toAccount.getAmount().subtract(transaction.getAmount()));
        fromAccount.setAmount(fromAccount.getAmount().add(transaction.getAmount()));

        accountRepository.saveAll(Arrays.asList(toAccount, fromAccount));

        transaction.setStatus(TransactionStatus.REVERT.getCode());
        return transactionRepository.save(transaction);
    }

    private TransactionDTO scheduledTransaction(TransactionDTO transactionDTO, Account toAccount, Account fromAccount, ApplicationUser applicationUser) {

        if (Objects.isNull(transactionDTO.getParcels())) {
            throw new BadRequestException("Para uma transação agendada o numero de parcelas deve ser informado");
        }

        BigDecimal amountParcel = transactionDTO.getAmount().divide(new BigDecimal(transactionDTO.getParcels())).setScale(2);

        for (int i = 0; i < transactionDTO.getParcels(); i++) {
            scheduleTransactionRepository.save(new ScheduledTransaction(toAccount, fromAccount, transactionDTO.getParcels() - i, amountParcel, applicationUser));
        }

        return transactionDTO;
    }

    private TransactionDTO pixTransaction(TransactionDTO transactionDTO, Account toAccount, Account fromAccount, ApplicationUser applicationUser) {
        fromAccount.setAmount(fromAccount.getAmount().subtract(transactionDTO.getAmount()));
        toAccount.setAmount(toAccount.getAmount().add(transactionDTO.getAmount()));

        accountRepository.saveAll(Arrays.asList(toAccount, fromAccount));
        transactionRepository.save(new Transaction(toAccount, fromAccount, transactionDTO.getAmount(), applicationUser, TransactionStatus.SUCESS.getCode()));

        return transactionDTO;
    }
}
