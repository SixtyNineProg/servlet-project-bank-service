package by.clevertec.klimov.cleverbank.entity;

import com.opencsv.bean.CsvBindByName;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Bank implements Serializable {

  @Serial private static final long serialVersionUID = 1905122041950251207L;

  @CsvBindByName(column = "id")
  private long id;

  @CsvBindByName(column = "name")
  private String name;

  private List<User> users;

  public void setUsers(List<User> users) {
    this.users = users.stream().filter(user -> user.getBankId() == id).toList();
  }
}
