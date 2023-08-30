package by.clevertec.klimov.cleverbank.entity;

import lombok.Data;

@Data
public class User {
    private String name;
    private Account account;

    public User(String name, Account account) {
        this.name = name;
        this.account = account;
    }
}
