package com.project.wallet.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "wallet")
public class WalletDto {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "wallet_id")
    private Integer walletId;

    @OneToMany(mappedBy="wallet", cascade=CascadeType.ALL)
    @JsonManagedReference
    private Set<TransactionDto> txns;

    @Column(name = "balance")
    private double balance;

    @Column(name = "created_date")
    private String createdDate;

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public Set<TransactionDto> getTxns() {
        return txns;
    }

    public void setTxns(Set<TransactionDto> txns) {
        this.txns = txns;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public WalletDto(double balance) {
        this.balance = balance;
    }

    public WalletDto(){};
}
