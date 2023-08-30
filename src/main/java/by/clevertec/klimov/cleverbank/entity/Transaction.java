package by.clevertec.klimov.cleverbank.entity;

import lombok.Data;

@Data
public class Transaction {
    private double amount;

    public Transaction(double amount) {
        this.amount = amount;
    }
}
