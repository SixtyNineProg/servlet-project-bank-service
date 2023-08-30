package by.clevertec.klimov.cleverbank.entity;

import lombok.Data;

import java.util.List;

@Data
public class Account {
    private double balance;
    private List<Transaction> transactions;

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
