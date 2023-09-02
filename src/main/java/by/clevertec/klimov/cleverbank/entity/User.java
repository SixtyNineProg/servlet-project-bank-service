package by.clevertec.klimov.cleverbank.entity;

import by.clevertec.klimov.cleverbank.exception.ServiceException;
import java.util.Objects;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

//  private long id;

  private String name;

  private Account account;

  public double getBalance() {
    if (Objects.nonNull(account)) {
      return account.getBalance();
    } else {
      throw new ServiceException("Account is empty");
    }
  }
}
