package by.clevertec.klimov.cleverbank.test_data;

import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.entity.User;
import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder(setterPrefix = "with", toBuilder = true)
@Accessors(chain = true)
public class BankTestData {

  @Builder.Default private long id = 1;

  @Builder.Default private String name = "Alfa";

  @Builder.Default private List<User> users = Collections.emptyList();

  public Bank buildBank() {
    return Bank.builder().id(id).name(name).users(users).build();
  }
}
