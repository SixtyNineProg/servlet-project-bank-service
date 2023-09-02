package by.clevertec.klimov.cleverbank.service.impl;

import by.clevertec.klimov.cleverbank.entity.User;
import by.clevertec.klimov.cleverbank.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
  @Override
  public int create(User entity) {
    return 0;
  }

  @Override
  public Optional<User> readById(Long aLong) {
    return Optional.empty();
  }

  @Override
  public <S extends User> S update(S entity) {
    return null;
  }

  @Override
  public void deleteById(Long aLong) {

  }
}
