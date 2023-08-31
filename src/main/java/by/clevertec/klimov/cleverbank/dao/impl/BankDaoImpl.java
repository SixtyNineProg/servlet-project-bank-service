package by.clevertec.klimov.cleverbank.dao.impl;

import by.clevertec.klimov.cleverbank.dao.BankDao;
import by.clevertec.klimov.cleverbank.entity.Bank;
import java.util.Optional;

public class BankDaoImpl implements BankDao {
  @Override
  public <S extends Bank> S save(S entity) {
    return null;
  }

  @Override
  public Optional<Bank> findById(Long aLong) {
    return Optional.empty();
  }

  @Override
  public void deleteById(Long aLong) {

  }
}
