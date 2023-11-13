package by.clevertec.klimov.cleverbank.entity;

import com.opencsv.bean.CsvBindByName;
import java.util.List;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
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

  public synchronized double getBalance() {
    return account.getBalance();
  }

  public synchronized void setAccount(List<Account> accounts) {
    this.account =
        accounts.stream().filter(acc -> acc.getId() == accountId).findFirst().orElse(null);
  }

  public synchronized void setBank(List<Bank> banks) {
    this.bank = banks.stream().filter(b -> b.getId() == bankId).findFirst().orElse(null);
  }

  public synchronized void addToBalance(double amount) {
    account.addToBalance(amount);
  }

  public synchronized void unbalance(double amount) {
    account.unbalance(amount);
  }

  public synchronized void calculateInterest(int percent) {
    account.calculateInterest(percent);
  }

  public synchronized void addTransaction(Transaction transaction) {
    account.addTransaction(transaction);
  }

  public synchronized List<Transaction> getTransactions() {
    return account.getTransactions();
  }

  public synchronized boolean isExistTransaction(Transaction transaction) {
    return getTransactions().stream().anyMatch(transact -> transact.equals(transaction));
  }

  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", accountId="
        + accountId
        + ", bankId="
        + bankId
        + ", account="
        + account
        + '}';
  }
}
