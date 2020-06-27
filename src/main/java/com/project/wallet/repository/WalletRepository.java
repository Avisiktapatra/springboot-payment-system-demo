package com.project.wallet.repository;

import com.project.wallet.dto.UserDetailsDto;
import com.project.wallet.dto.WalletDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface WalletRepository extends JpaRepository<WalletDto, Integer> {

        WalletDto save (WalletDto walletDetails);
        WalletDto getByWalletId(int id);
    }
