package com.project.wallet.controller;

import com.project.wallet.dto.TransactionDetailsDto;
import com.project.wallet.dto.TransactionDto;
import com.project.wallet.service.TransactionServiceImpl;
import com.project.wallet.service.WalletServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/wallet/v1")
public class WalletController {

    private WalletServiceImpl walletServiceImpl;
    private TransactionServiceImpl transactionServiceImpl;


    @Autowired
    public void setLoginService(WalletServiceImpl walletServiceImpl,TransactionServiceImpl transactionServiceImpl) {
        this.walletServiceImpl = walletServiceImpl;
        this.transactionServiceImpl = transactionServiceImpl;
    }

    @PostMapping("/initiateTransaction")
    public ResponseEntity<TransactionDto> addMoneyToWallet(@RequestBody TransactionDetailsDto txnDetailsDto){
        TransactionDto transactionDto = transactionServiceImpl.createTransaction(txnDetailsDto);
        if(transactionDto == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionDto);

    }

    @GetMapping("/transactions/{user_id}")
    public ResponseEntity<Set<TransactionDto>> listAllTransactions(@PathVariable("user_id") Integer userId){
        Set<TransactionDto> transactionsList = transactionServiceImpl.listTransactions(userId);
        if(transactionsList == null || transactionsList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(transactionsList);
    }

    @PostMapping("/reverseTransaction")
    public ResponseEntity<TransactionDto> reverseTransaction(@RequestBody TransactionDetailsDto txnDetailsDto){
        TransactionDto transactionDto = transactionServiceImpl.transferAmount(txnDetailsDto);
        if(transactionDto == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionDto);
    }

    @GetMapping("/transaction/details/{txn_id}")
    public ResponseEntity<TransactionDto> fetchTransaction(@PathVariable("txn_id") Integer txnId){
        TransactionDto transaction = transactionServiceImpl.findTransactionDetails(txnId);
        if(transaction == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }
}
