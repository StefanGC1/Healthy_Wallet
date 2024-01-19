package com.example.healthy_wallet.ui.admin_scene_classes;

import com.example.healthy_wallet.AbstractTransaction;
import com.example.healthy_wallet.ui.TransactionListController;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionInfo {
    private int transactionID;
    private AbstractTransaction transaction;
    private LocalDateTime transactionTimestamp;

    public TransactionInfo(int _transactionID, AbstractTransaction _transaction, LocalDateTime _transactionTimestamp) {
        this.transactionID = _transactionID;
        this.transaction = _transaction;
        this.transactionTimestamp = _transactionTimestamp;
    }

    public int getTransactionID() { return transactionID; }
    public AbstractTransaction getTransaction() { return transaction; }
    public LocalDateTime getTransactionTimestamp() { return transactionTimestamp; }
}
