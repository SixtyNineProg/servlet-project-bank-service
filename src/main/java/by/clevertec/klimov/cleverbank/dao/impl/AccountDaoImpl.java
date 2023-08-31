package by.clevertec.klimov.cleverbank.dao.impl;

import by.clevertec.klimov.cleverbank.dao.AccountDao;
import by.clevertec.klimov.cleverbank.entity.Account;

import java.util.Optional;

public class AccountDaoImpl implements AccountDao {
  @Override
  public <S extends Account> S save(S entity) {
    return null;
  }

  @Override
  public Optional<Account> findById(Long aLong) {
    return Optional.empty();
  }

  @Override
  public void deleteById(Long aLong) {

  }
}
