package by.clevertec.klimov.cleverbank.entity;

import by.clevertec.klimov.cleverbank.emum.TransactionType;
import by.clevertec.klimov.cleverbank.exception.ServiceException;
import com.opencsv.bean.CsvBindByName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

  @CsvBindByName(column = "id")
  private long id;

  @CsvBindByName(column = "balance")
  private double balance;

  private List<Transaction> transactions;

  public void addTransaction(Transaction transaction) {
    if (Objects.isNull(transactions)) {
      transactions = new ArrayList<>();
    }
    transactions.add(transaction);
  }

  public void handleTransaction(Transaction transaction) {
    if (transaction.getType().equals(TransactionType.DEPOSIT)) { 
      deposit(transaction);
    } else {
      withdrawal(transaction);
    }
  }

  private void deposit(Transaction transaction) {
    double amount = transaction.getAmount();
      if (amount > 0) {
        transactions.add(transaction);
        balance += amount;
      } else {
        throw new ServiceException("Amount must be greater than zero");
      }
  }

  private void withdrawal(Transaction transaction) {
      double amount = transaction.getAmount();
      if (amount > 0) {
        if (balance >= amount) {
          transactions.add(transaction);
          balance -= amount;
        } else {
          throw new ServiceException("Insufficient funds");
        }
      } else {
        throw new ServiceException("Amount must be greater than zero");
      }
  }

  public int getTransactionsSize() {
    return Objects.nonNull(transactions) ? transactions.size() : 0;
  }
}
