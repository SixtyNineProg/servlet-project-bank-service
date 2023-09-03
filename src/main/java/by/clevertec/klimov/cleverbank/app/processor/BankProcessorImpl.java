package by.clevertec.klimov.cleverbank.app.processor;

import by.clevertec.klimov.cleverbank.emum.TransactionType;
import by.clevertec.klimov.cleverbank.entity.Transaction;
import by.clevertec.klimov.cleverbank.entity.User;
import by.clevertec.klimov.cleverbank.exception.ServiceException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

/**
 * Banking class.
 * @version 1.0.0
 */
@Slf4j
public class BankProcessorImpl implements BankProcessor {
/**
* Transferring funds from one account to another.
 * @param sender client sender
 * @param receiver client receiver
 * @param amount transfer amount
*/
  @Override
  public void transfer(User sender, User receiver, double amount) {
    Transaction transaction =
        Transaction.builder()
            .uuid(UUID.randomUUID())
            .amount(amount)
            .date(new Date())
            .build();
    try {
      transfer(sender, receiver, transaction);
      CheckProcessor.printCheck(sender, receiver, transaction);
    } catch (ServiceException e) {
      log.error("Transfer finished with error");
    }
  }

/**
* Deposit to the user's account.
 * @param user client
 * @param amount deposit amount
*/
  @Override
  public void deposit(User user, double amount) {
    Transaction transaction =
        Transaction.builder()
            .amount(amount)
            .uuid(UUID.randomUUID())
            .date(new Date())
            .type(TransactionType.DEPOSIT)
            .build();
      deposit(user, transaction);
  }


/**
* Withdrawal of funds from the user's account.
 * @param user client
 * @param amount withdrawal amount
*/
  @Override
  public void withdrawal(User user, double amount) {
    Transaction transaction =
        Transaction.builder()
            .uuid(UUID.randomUUID())
            .amount(amount)
            .date(new Date())
            .type(TransactionType.WITHDRAWAL)
            .build();
      withdrawal(user, transaction);
  }

  private void deposit(User user, Transaction transaction) {
    double amount = transaction.getAmount();
    if (amount > 0) {
      try {
        user.addToBalance(amount);
      } catch (Exception e) {
        throw new ServiceException("Add to balance failed with error", e);
      }
      user.addTransaction(transaction);
    } else {
      log.error("Amount must be greater than zero");
    }
  }

  private void withdrawal(User user, Transaction transaction) {
    double transactionAmount = transaction.getAmount();
    if (transactionAmount > 0) {
      if (user.getBalance() >= transactionAmount) {
        try {
          user.unbalance(transactionAmount);
        } catch (Exception e) {
          throw new ServiceException("Unbalance failed with error", e);
        }
        user.addTransaction(transaction);
      } else {
        log.error("Insufficient funds");
      }
    } else {
      log.error("Amount must be greater than zero");
    }
  }

  private void transfer(User sender, User receiver, Transaction transaction) {
    if (Objects.nonNull(sender) && Objects.nonNull(receiver)) {
      transaction.setType(TransactionType.WITHDRAWAL);
      withdrawal(sender, transaction);
      transaction.setType(TransactionType.DEPOSIT);
      try{
        deposit(receiver, transaction);
      } catch (ServiceException e) {
        deposit(sender, transaction.getAmount());
        throw new ServiceException(e);
      }
    } else {
      log.error("User not found");
    }
  }
}
