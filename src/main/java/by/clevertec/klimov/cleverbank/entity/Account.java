package by.clevertec.klimov.cleverbank.entity;

import java.util.List;
import lombok.Data;

@Data
public class Account {
    
    private double balance;
    private List<Transaction> transactions;

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
