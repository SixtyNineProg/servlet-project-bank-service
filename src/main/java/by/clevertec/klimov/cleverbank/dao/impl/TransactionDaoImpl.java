package by.clevertec.klimov.cleverbank.dao.impl;

import by.clevertec.klimov.cleverbank.dao.TransactionDao;
import by.clevertec.klimov.cleverbank.entity.Transaction;

import java.util.Optional;

public class TransactionDaoImpl implements TransactionDao {
  @Override
  public <S extends Transaction> S save(S entity) {
    return null;
  }

  @Override
  public Optional<Transaction> findById(Long aLong) {
    return Optional.empty();
  }

  @Override
  public void deleteById(Long aLong) {

  }
}
