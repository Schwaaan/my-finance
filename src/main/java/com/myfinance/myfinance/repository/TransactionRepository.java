package com.myfinance.myfinance.repository;

import com.myfinance.myfinance.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByApplicationUser_Id(Integer userId);
}

