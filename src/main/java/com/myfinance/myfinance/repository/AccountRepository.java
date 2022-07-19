package com.myfinance.myfinance.repository;

import com.myfinance.myfinance.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByApplicationUser_Id(Integer id);
}
