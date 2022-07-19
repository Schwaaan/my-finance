package com.myfinance.myfinance.repository;

import com.myfinance.myfinance.domain.ScheduledTransaction;
import com.myfinance.myfinance.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduledTransactionRepository extends JpaRepository<ScheduledTransaction, Integer> {

    List<ScheduledTransaction> findAllByApplicationUser_Id(Integer userId);

}
