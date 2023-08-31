package by.clevertec.klimov.cleverbank.service.impl;

import by.clevertec.klimov.cleverbank.entity.Transaction;
import by.clevertec.klimov.cleverbank.service.TransactionService;

import java.util.Optional;

public class TransactionServiceImpl implements TransactionService {
  @Override
  public <S extends Transaction> S create(S entity) {
    return null;
  }

  @Override
  public Optional<Transaction> readById(Long aLong) {
    return Optional.empty();
  }

  @Override
  public <S extends Transaction> S update(S entity) {
    return null;
  }

  @Override
  public void deleteById(Long aLong) {

  }
}
