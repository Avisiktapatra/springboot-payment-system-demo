package com.project.wallet.service;

import com.project.wallet.dto.TransactionDetailsDto;
import com.project.wallet.dto.TransactionDto;
import com.project.wallet.dto.UserDetailsDto;
import com.project.wallet.dto.WalletDto;
import com.project.wallet.exception.InsufficientFundsException;
import com.project.wallet.exception.TransactionNotAvailableException;
import com.project.wallet.exception.UserDoesNotExistException;
import com.project.wallet.repository.TransactionRepository;
import com.project.wallet.repository.UserRepository;
import com.project.wallet.repository.WalletRepository;
import com.project.wallet.util.WalletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    static String fundsType = "CREDIT";
    static String reversalFundsType = "DEBIT";
    static String txnStatus = "SUCCESS";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionDto createTransaction(TransactionDetailsDto txnDetailsDto){

        WalletDto walletDto = getWalletDto(txnDetailsDto.getUserId());
        Double updatedBalance = walletDto.getBalance() + txnDetailsDto.getAmount();
        walletDto.setBalance(updatedBalance);
        String date = WalletUtil.getCurrentTimestamp();
        TransactionDto transactionDto = new TransactionDto(updatedBalance, date, txnDetailsDto.getUserId(),
                txnDetailsDto.getUserId(), txnStatus, fundsType, txnDetailsDto.getAmount(), walletDto);

        TransactionDto savedTxnDto = getTransactionDto(walletDto, transactionDto);
        return savedTxnDto;
    }

    private TransactionDto getTransactionDto(WalletDto walletDto, TransactionDto transactionDto) {
        Set<TransactionDto> txnList = walletDto.getTxns();
        if(txnList == null){
            txnList = new HashSet<>();
        }
        txnList.add(transactionDto);
        walletDto.setTxns(txnList);
        return transactionRepository.save(transactionDto);
    }

    public Set<TransactionDto> listTransactions(Integer userId){
        WalletDto walletDto = getWalletDto(userId);
        Set<TransactionDto> transactionDtoList = walletDto.getTxns();
        return transactionDtoList;
    }

    public TransactionDto findTransactionDetails(Integer txnId){
        TransactionDto transactionDto = transactionRepository.findByTxnId(txnId);
        if(transactionDto == null)
            throw new TransactionNotAvailableException(txnId);
        return transactionDto;
    }

    public TransactionDto transferAmount(TransactionDetailsDto txnDetailsDto) throws InsufficientFundsException {
        TransactionDto savedTxnDto;
        TransactionDto transactionDto = transactionRepository.findByTxnId(txnDetailsDto.getTxnId());
        Integer toUserId = txnDetailsDto.getUserId();
        Integer fromUserId = txnDetailsDto.getFromUserId();
        WalletDto fromUserWalletDto = getWalletDto(fromUserId);
        WalletDto toUserWalletDto = getWalletDto(toUserId);
        if(transactionDto == null)
            throw new TransactionNotAvailableException(txnDetailsDto.getTxnId());
        double availableBalance = fromUserWalletDto.getBalance();

            if (availableBalance > 0) {
                synchronized (this) {
                if (fromUserId == toUserId) {
                    if (availableBalance >= txnDetailsDto.getAmount()) {
                        fromUserWalletDto.setBalance(availableBalance - txnDetailsDto.getAmount());
                        TransactionDto reversalTransactionDto =
                                new TransactionDto(availableBalance - txnDetailsDto.getAmount(), WalletUtil.getCurrentTimestamp(),
                                        toUserId, fromUserId, txnStatus, reversalFundsType, txnDetailsDto.getAmount(), fromUserWalletDto);
                        savedTxnDto = getTransactionDto(fromUserWalletDto, reversalTransactionDto);
                    } else throw new InsufficientFundsException(txnDetailsDto.getAmount() - availableBalance);
                } else {
                    double transferCharge = WalletUtil.computeTransferCharges(txnDetailsDto.getAmount());
                    if (availableBalance >= txnDetailsDto.getAmount() + transferCharge) {

                        double updatedBalanceFromUser = availableBalance - (transferCharge + txnDetailsDto.getAmount());
                        double updatedBalanceToUser = toUserWalletDto.getBalance() + txnDetailsDto.getAmount();
                        fromUserWalletDto.setBalance(updatedBalanceFromUser);
                        toUserWalletDto.setBalance(updatedBalanceToUser);
                        TransactionDto toUserTransactionDto =
                                new TransactionDto(updatedBalanceToUser,
                                        WalletUtil.getCurrentTimestamp(),
                                        toUserId, fromUserId, txnStatus, fundsType,
                                        txnDetailsDto.getAmount(), toUserWalletDto);
                        getTransactionDto(toUserWalletDto, toUserTransactionDto);

                        TransactionDto fromUserTransactionDto =
                                new TransactionDto(updatedBalanceFromUser, WalletUtil.getCurrentTimestamp(), toUserId,
                                        fromUserId, txnStatus, reversalFundsType,
                                        txnDetailsDto.getAmount(), fromUserWalletDto);
                        savedTxnDto = getTransactionDto(fromUserWalletDto, fromUserTransactionDto);
                    } else
                        throw new InsufficientFundsException((txnDetailsDto.getAmount() + transferCharge) - availableBalance);
                }
            }
        }
        else throw new InsufficientFundsException(txnDetailsDto.getAmount());


        return savedTxnDto;
    }

    private WalletDto getWalletDto(Integer userId) {
        Optional<UserDetailsDto> userDetailsDto = userRepository.findById(userId);
        if(!userDetailsDto.isPresent()){
            throw new UserDoesNotExistException(String.valueOf(userId));
        }
        return walletRepository.getByWalletId(userDetailsDto.get().getWallet().getWalletId());
    }
}
