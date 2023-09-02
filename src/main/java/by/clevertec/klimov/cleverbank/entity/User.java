package by.clevertec.klimov.cleverbank.entity;

import by.clevertec.klimov.cleverbank.exception.ServiceException;
import com.opencsv.bean.CsvBindByName;
import java.util.List;
import java.util.Objects;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @CsvBindByName(column = "id")
  private long id;

  @CsvBindByName(column = "name")
  private String name;

  @CsvBindByName(column = "account_id")
  private long accountId;

  @CsvBindByName(column = "bank_id")
  private long bankId;

  private Account account;

  private Bank bank;

  public double getBalance() {
    if (Objects.nonNull(account)) {
      return account.getBalance();
    } else {
      throw new ServiceException("Account is empty");
    }
  }
  
  public void setAccount(List<Account> accounts) {
    this.account = accounts.stream().filter(acc -> acc.getId() == accountId).findFirst().orElse(null);
  }

  public void setBank(List<Bank> banks) {
    this.bank = banks.stream().filter(b -> b.getId() == bankId).findFirst().orElse(null);
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", accountId=" + accountId +
            ", bankId=" + bankId +
            ", account=" + account +
            '}';
  }
}
