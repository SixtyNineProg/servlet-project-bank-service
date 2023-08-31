package by.clevertec.klimov.cleverbank.service.impl;

import by.clevertec.klimov.cleverbank.entity.Account;
import by.clevertec.klimov.cleverbank.service.AccountService;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {
  @Override
  public <S extends Account> S create(S entity) {
    return null;
  }

  @Override
  public Optional<Account> readById(Long aLong) {
    return Optional.empty();
  }

  @Override
  public <S extends Account> S update(S entity) {
    return null;
  }

  @Override
  public void deleteById(Long aLong) {

  }
}
