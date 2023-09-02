package by.clevertec.klimov.cleverbank.entity;

import by.clevertec.klimov.cleverbank.exception.ServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvBindByName;
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

  public void addUser(User user) {
    if (Objects.isNull(users)) {
      users = new ArrayList<>();
    }
    users.add(user);
  }

  public void setUsers(List<User> users) {
    this.users = users.stream().filter(user -> user.getBankId() == id).collect(Collectors.toList());
  }

  protected boolean isExistUser(User user) {
    return Objects.nonNull(users)
        && Objects.nonNull(user)
        && users.stream().anyMatch(u -> u.getId() == user.getId());
  }

  protected Optional<User> getUser(User user) {
    return Objects.nonNull(users)
        ? users.stream().filter(u -> u.getId() == user.getId()).findFirst()
        : Optional.empty();
  }

  public int getUsersSize() {
    return Objects.nonNull(users) ? users.size() : 0;
  }

  public double getUserBalance(User user) {
    if (isExistUser(user)) {
      return getUser(user)
          .orElseThrow(() -> new ServiceException("User not found"))
          .getBalance();
    } else {
      throw new ServiceException("User not found");
    }
  }
}
