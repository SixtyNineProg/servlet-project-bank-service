package by.clevertec.klimov.cleverbank.entity;

import by.clevertec.klimov.cleverbank.emum.TransactionType;
import by.clevertec.klimov.cleverbank.exception.ServiceException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Bank {

  //  private long id;

  private String name;

  private List<User> users;

  public void addUser(User user) {
    if (Objects.isNull(users)) {
      users = new ArrayList<>();
    }
    users.add(user);
  }

  public void deposit(User user, double amount) {
    if (isExistUser(user)) {
      Account account = user.getAccount();
      if (amount > 0) {
        account.addTransaction(
            Transaction.builder()
                .type(TransactionType.DEPOSIT)
                .amount(amount)
                .date(LocalDateTime.now())
                .build());
        account.setBalance(account.getBalance() + amount);
      } else {
        throw new ServiceException("Amount must be greater than zero");
      }
    } else {
      throw new ServiceException("User not found in the bank");
    }
  }

  public void withdraw(User user, double amount) {
    if (isExistUser(user)) {
      Account account = user.getAccount();
      double balance = account.getBalance();
      if (amount > 0) {
        if (balance >= amount) {
          account.addTransaction(
              Transaction.builder()
                  .type(TransactionType.DEPOSIT)
                  .amount(amount)
                  .date(LocalDateTime.now())
                  .build());
          account.setBalance(account.getBalance() - amount);
        } else {
          throw new ServiceException("Insufficient funds");
        }
      } else {
        throw new ServiceException("Amount must be greater than zero");
      }
    } else {
      throw new ServiceException("User not found in the bank");
    }
  }

  protected boolean isExistUser(User user) {
    return Objects.nonNull(users) && users.stream().anyMatch(u -> u.equals(user));
  }

  protected boolean isExistUser(String userName) {
    return Objects.nonNull(users) && users.stream().anyMatch(u -> u.getName().equals(userName));
  }

  protected Optional<User> getUser(String userName) {
    return Objects.nonNull(users)
        ? users.stream().filter(user -> user.getName().equals(userName)).findFirst()
        : Optional.empty();
  }

  public int getUsersSize() {
    return Objects.nonNull(users) ? users.size() : 0;
  }

  public double getUserBalance(String userName) {
    if (isExistUser(userName)) {
      return getUser(userName)
          .orElseThrow(() -> new ServiceException("User not found"))
          .getBalance();
    } else {
      throw new ServiceException("User not found");
    }
  }
}
