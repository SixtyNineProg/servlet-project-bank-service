package by.clevertec.klimov.cleverbank.entity;

import java.util.Date;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransactionWithdrawal extends Transaction{

  private User sandlerUser;

  public TransactionWithdrawal(long id, double amount, Date date, UUID uuid, String authorizationCode, User sandlerUser) {
    super(id, amount, date, uuid, authorizationCode);
    this.sandlerUser = sandlerUser;
  }
}
