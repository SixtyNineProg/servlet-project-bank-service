package by.clevertec.klimov.cleverbank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

  private long id;

  private String name;

  private long accountId;

  private long bankId;
}
