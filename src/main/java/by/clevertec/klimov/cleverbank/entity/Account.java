package by.clevertec.klimov.cleverbank.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {

  //    private long id;

  private double balance;

  private List<Transaction> transactions;

  public void addTransaction(Transaction transaction) {
    if (Objects.isNull(transactions)) {
      transactions = new ArrayList<>();
    }
    transactions.add(transaction);
  }
}
