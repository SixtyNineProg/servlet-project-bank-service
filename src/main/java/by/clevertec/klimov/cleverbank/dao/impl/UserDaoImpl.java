package by.clevertec.klimov.cleverbank.dao.impl;

import by.clevertec.klimov.cleverbank.dao.UserDao;
import by.clevertec.klimov.cleverbank.entity.User;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
  @Override
  public <S extends User> S save(S entity) {
    return null;
  }

  @Override
  public Optional<User> findById(Long aLong) {
    return Optional.empty();
  }

  @Override
  public void deleteById(Long aLong) {

  }
}
