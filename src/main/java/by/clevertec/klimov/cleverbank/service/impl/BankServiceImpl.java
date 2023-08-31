package by.clevertec.klimov.cleverbank.service.impl;

import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.service.BankService;

import java.util.Optional;

public class BankServiceImpl implements BankService {
  @Override
  public <S extends Bank> S create(S entity) {
    return null;
  }

  @Override
  public Optional<Bank> readById(Long aLong) {
    return Optional.empty();
  }

  @Override
  public <S extends Bank> S update(S entity) {
    return null;
  }

  @Override
  public void deleteById(Long aLong) {

  }
}
