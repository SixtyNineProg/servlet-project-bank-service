package by.clevertec.klimov.cleverbank.entity;

import java.util.Date;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransactionTransfer extends Transaction {

  private User recipientUser;

  private User sandlerUser;

  TransactionTransfer(long id, double amount, Date date, UUID uuid, String authorizationCode) {
    super(id, amount, date, uuid, authorizationCode);
  }
}
