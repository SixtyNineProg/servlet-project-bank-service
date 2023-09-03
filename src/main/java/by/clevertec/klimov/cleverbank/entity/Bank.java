package by.clevertec.klimov.cleverbank.entity;

import com.opencsv.bean.CsvBindByName;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bank {

  @CsvBindByName(column = "id")
  private long id;

  @CsvBindByName(column = "name")
  private String name;

  private List<User> users;

  public void setUsers(List<User> users) {
    this.users = users.stream().filter(user -> user.getBankId() == id).toList();
  }
}
