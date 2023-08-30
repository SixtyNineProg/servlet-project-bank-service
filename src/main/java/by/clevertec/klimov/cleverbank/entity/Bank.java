package by.clevertec.klimov.cleverbank.entity;

import lombok.Data;

import java.util.List;

@Data
public class Bank {
    private List<User> users;

    public void addUser(User user) {
        users.add(user);
    }

    public void deposit(User user, double amount) {
        Account account = user.getAccount();
        Transaction transaction = new Transaction(amount);
        account.addTransaction(transaction);
        account.setBalance(account.getBalance() + amount);
    }
}
