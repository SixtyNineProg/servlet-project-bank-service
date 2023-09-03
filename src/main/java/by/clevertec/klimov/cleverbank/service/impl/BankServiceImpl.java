package by.clevertec.klimov.cleverbank.service.impl;

import by.clevertec.klimov.cleverbank.dao.BankDao;
import by.clevertec.klimov.cleverbank.dao.impl.BankDaoImpl;
import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.exception.ServiceException;
import by.clevertec.klimov.cleverbank.service.BankService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BankServiceImpl implements BankService {

  public static final String ERROR_OCCURRED_WHILE_CREATE_BANK = "An error occurred while create bank";
  private final BankDao bankDao = new BankDaoImpl();

  @Override
  public int create(Bank bank) {
    log.debug("Create bank: {}", bank);
    try {
      return bankDao.save(bank);
    } catch (Exception e) {
      log.error(ERROR_OCCURRED_WHILE_CREATE_BANK, e);
      throw new ServiceException(ERROR_OCCURRED_WHILE_CREATE_BANK, e);
    }
  }

  @Override
  public Optional<Bank> readById(Long id) {
    log.info("Read movie by id = {}", id);
    return Optional.empty();
  }

  @Override
  public <S extends Bank> S update(S entity) {
    return null;
  }

  @Override
  public void deleteById(Long aLong) {}
}
