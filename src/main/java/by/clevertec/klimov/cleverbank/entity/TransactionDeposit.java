package by.clevertec.klimov.cleverbank.entity;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class TransactionDeposit extends Transaction {

  private User recipientUser;
}
