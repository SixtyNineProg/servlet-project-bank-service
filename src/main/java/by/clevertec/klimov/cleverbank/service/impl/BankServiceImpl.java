package by.clevertec.klimov.cleverbank.service.impl;

import by.clevertec.klimov.cleverbank.aspect.annotation.CreateBank;
import by.clevertec.klimov.cleverbank.aspect.annotation.DeleteBank;
import by.clevertec.klimov.cleverbank.aspect.annotation.ReadBank;
import by.clevertec.klimov.cleverbank.aspect.annotation.UpdateBank;
import by.clevertec.klimov.cleverbank.dao.BankDao;
import by.clevertec.klimov.cleverbank.dao.impl.BankDaoImpl;
import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.mapper.Mapper;
import by.clevertec.klimov.cleverbank.mapper.impl.BankMapper;
import by.clevertec.klimov.cleverbank.service.BankService;
import java.util.Optional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class BankServiceImpl implements BankService {

  public static final String ERROR_OCCURRED_WHILE_CREATE_BANK =
      "An error occurred while create bank";
  private final BankDao bankDao = new BankDaoImpl();
  private final Mapper<Bank, BankDto> bankMapper = new BankMapper();

  @Override
  public long create(BankDto bank) {
    log.debug("Create bank: {}", bank);
    return bankDao.save(bankMapper.mapToEntity(bank, Bank.class));
  }

  @Override
  public Optional<BankDto> readById(Long id) {
    log.info("Read bank by id = {}", id);
    Optional<Bank> optionalBank = bankDao.findById(id);
    return optionalBank.map(bank -> bankMapper.mapToDto(bank, BankDto.class));
  }

  @Override
  public int update(BankDto bankDto) {
    log.debug("Update bank: {}", bankDto);
    Optional<Bank> optionalBank = bankDao.findById(bankDto.getId());
    if (optionalBank.isPresent()) {
      Bank dbBank = optionalBank.get();
      bankMapper.merge(bankDto, dbBank);
      log.info("Update user with id = {} successfully", bankDto.getId());
      return bankDao.update(dbBank);
    } else {
      log.info(String.format("Bank with id = %s not found", bankDto.getId()));
      return 0;
    }
  }

  @Override
  public int deleteById(Long id) {
    log.info("Delete bank by id = {}", id);
    return bankDao.deleteById(id);
  }
}
