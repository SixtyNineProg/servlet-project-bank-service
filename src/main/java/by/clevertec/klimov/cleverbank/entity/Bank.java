package by.clevertec.klimov.cleverbank.entity;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Bank {

    private long id;

    private String name;

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
