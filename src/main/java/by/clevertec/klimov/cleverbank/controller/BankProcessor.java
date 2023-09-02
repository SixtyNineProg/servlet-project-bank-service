package by.clevertec.klimov.cleverbank.controller;

import by.clevertec.klimov.cleverbank.emum.TransactionType;
import by.clevertec.klimov.cleverbank.entity.Transaction;
import by.clevertec.klimov.cleverbank.entity.User;
import by.clevertec.klimov.cleverbank.exception.ServiceException;
import java.util.Objects;

public class BankProcessor {
  public void transfer(Transaction transaction, User sender, User receiver) {
    if (Objects.nonNull(sender) && Objects.nonNull(receiver)) {
      transaction.setType(TransactionType.WITHDRAWAL);
      withdrawal(transaction, sender);
      transaction.setType(TransactionType.DEPOSIT);
      deposit(transaction, receiver);
    } else {
      throw new ServiceException("User not found");
    }
  }

  public void deposit(Transaction transaction, User user) {
    double amount = transaction.getAmount();
    if (amount > 0) {
      user.addToBalance(amount);
      user.addTransaction(transaction);
    } else {
      throw new ServiceException("Amount must be greater than zero");
    }
  }

  public void withdrawal(Transaction transaction, User user) {
    double amount = transaction.getAmount();
    if (amount > 0) {
      if (user.getBalance() >= amount) {
        user.unbalance(amount);
        user.addTransaction(transaction);
      } else {
        throw new ServiceException("Insufficient funds");
      }
    } else {
      throw new ServiceException("Amount must be greater than zero");
    }
  }
}
