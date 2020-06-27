package com.project.wallet.service;

import com.project.wallet.dto.TransactionDetailsDto;
import com.project.wallet.dto.TransactionDto;

public interface TransactionService {

TransactionDto createTransaction(TransactionDetailsDto txnDetailsDto);
}
