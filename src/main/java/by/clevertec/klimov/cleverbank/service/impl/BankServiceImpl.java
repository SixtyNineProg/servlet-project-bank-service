package by.clevertec.klimov.cleverbank.service.impl;

import by.clevertec.klimov.cleverbank.dao.BankDao;
import by.clevertec.klimov.cleverbank.dao.impl.BankDaoImpl;
import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.service.BankService;
import java.util.Optional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class BankServiceImpl implements BankService {

  public static final String ERROR_OCCURRED_WHILE_CREATE_BANK = "An error occurred while create bank";
  private final BankDao bankDao = new BankDaoImpl();

  @Override
  public int create(Bank bank) {
    log.debug("Create bank: {}", bank);
    return bankDao.save(bank);
  }

  @Override
  public Optional<Bank> readById(Long id) {
    log.info("Read bank by id = {}", id);
    return bankDao.findById(id);
  }

  @Override
  public int update(Bank bank) {
    log.debug("Update bank: {}", bank);
    Optional<Bank> optionalBank = readById(bank.getId());
    if (optionalBank.isPresent()) {
      Bank dbBank = optionalBank.get();
      dbBank.setName(bank.getName());
      return bankDao.update(dbBank);
    } else {
      return 0;
    }
  }

  @Override
  public int deleteById(Long id) {
    log.info("Delete bank by id = {}", id);
    return bankDao.deleteById(id);
  }
}
