package com.project.wallet.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "txn_details")
public class TransactionDto {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name="txn_id")
    private Integer txnId;

    @ManyToOne
    @JoinColumn(name="wallet_id", nullable=false)
    @JsonBackReference
    private WalletDto wallet;

    @Column(name = "balance")
    private double updatedBalance;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "to_user_id")
    private Integer toUserId;

    @Column(name = "from_user_id")
    private Integer fromUserId;

    @Column(name = "status")
    private String status;

    private String type;

    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTxnId() {
        return txnId;
    }

    public void setTxnId(Integer txnId) {
        this.txnId = txnId;
    }

    public WalletDto getWallet() {
        return wallet;
    }

    public void setWallet(WalletDto wallet) {
        this.wallet = wallet;
    }

    public double getUpdatedBalance() {
        return updatedBalance;
    }

    public void setUpdatedBalance(double updatedBalance) {
        this.updatedBalance = updatedBalance;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TransactionDto(double balance, String createdDate, Integer toUserId, Integer fromUserId, String status,
            String type, double amount, WalletDto wallet) {
        this.updatedBalance = balance;
        this.createdDate = createdDate;
        this.toUserId = toUserId;
        this.fromUserId = fromUserId;
        this.status = status;
        this.type = type;
        this.amount = amount;
        this.wallet = wallet;
    }

    public TransactionDto(){};
}
