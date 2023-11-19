package by.clevertec.klimov.cleverbank.dto;

import by.clevertec.klimov.cleverbank.entity.User;
import java.util.Date;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDto {

  private long id;

  private double amount;

  private Date date;

  private UUID uuid;

  private String authorizationCode;

  private User receiver;

  private User sender;
}
