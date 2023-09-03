package by.clevertec.klimov.cleverbank.controller;

import by.clevertec.klimov.cleverbank.entity.User;

public interface BankProcessor {
  void transfer(User sender, User receiver, double amount);

  void deposit(User user, double amount);

  void withdrawal(User user, double amount);
}
