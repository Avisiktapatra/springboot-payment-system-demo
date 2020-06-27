package com.project.wallet.repository;

import com.project.wallet.dto.TransactionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDto, Integer> {

    TransactionDto save (TransactionDto txnDetails);
    TransactionDto findByTxnId(Integer txnId);
}
