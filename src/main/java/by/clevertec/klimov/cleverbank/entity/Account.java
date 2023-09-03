package by.clevertec.klimov.cleverbank.entity;

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

  public void addToBalance(double amount) {
    balance += amount;
  }

  public void unbalance(double amount) {
    balance -= amount;
  }

  public void calculateInterest(int percent) {
    balance += balance * ((double) percent /100);
  }

  public int getTransactionsSize() {
    return Objects.nonNull(transactions) ? transactions.size() : 0;
  }
}
