package by.clevertec.klimov.cleverbank.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@FieldNameConstants
public class AccountDto {

  private long id;

  private double balance;
}
