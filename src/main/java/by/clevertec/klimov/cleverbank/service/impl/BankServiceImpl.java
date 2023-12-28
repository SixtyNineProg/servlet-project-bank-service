package by.clevertec.klimov.cleverbank.service.impl;

import by.clevertec.klimov.cleverbank.configuration.ConfigurationLoader;
import by.clevertec.klimov.cleverbank.dao.BankDao;
import by.clevertec.klimov.cleverbank.dao.impl.BankDaoImpl;
import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.exception.DaoException;
import by.clevertec.klimov.cleverbank.exception.DaoNotFoundException;
import by.clevertec.klimov.cleverbank.exception.ServiceException;
import by.clevertec.klimov.cleverbank.mapper.BankMapper;
import by.clevertec.klimov.cleverbank.pdf.PdfWriter;
import by.clevertec.klimov.cleverbank.pdf.impl.BankPdfWriter;
import by.clevertec.klimov.cleverbank.service.BankService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BankServiceImpl implements BankService {

  public static final String BANK_WITH_ID_NOT_FOUND = "Bank with id = %s not found";
  private BankDao bankDao = new BankDaoImpl();
  private PdfWriter<BankDto> bankPdfWriter = new BankPdfWriter();

  @Override
  public long create(BankDto bankDto) {
    log.debug("Create bank: {}", bankDto);
    try {
      return bankDao.save(BankMapper.INSTANCE.toBank(bankDto));
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public Optional<BankDto> readById(Long id) {
    log.info("Read bank by id = {}", id);
    Optional<Bank> optionalBank = bankDao.findById(id);
    return optionalBank.map(BankMapper.INSTANCE::toBankDto);
  }

  @Override
  public List<BankDto> readAll() {
    log.info("Read all banks");
    List<Bank> banks = bankDao.findAll();
    return BankMapper.INSTANCE.toBankDto(banks);
  }

  @Override
  public List<BankDto> read(Integer offset, Integer limit) {
    if (Objects.isNull(offset) || offset < 0) {
      throw new ServiceException("Offset isn't correct");
    }
    if (Objects.isNull(limit) || limit < 0) {
      limit = ConfigurationLoader.getConfiguration().getPagination().getPageSize();
    }
    log.info("Read banks with offset: {}, limit: {}", offset, limit);
    List<Bank> banks = bankDao.find(offset, limit);
    return BankMapper.INSTANCE.toBankDto(banks);
  }

  @Override
  public int update(BankDto bankDto) {
    log.debug("Update bank: {}", bankDto);
    Optional<Bank> optionalBank = bankDao.findById(bankDto.getId());
    if (optionalBank.isPresent()) {
      Bank dbBank = optionalBank.get();
      dbBank = BankMapper.INSTANCE.merge(bankDto, dbBank);
      log.info("Update user with id = {} successfully", bankDto.getId());
      return bankDao.update(dbBank);
    } else {
      log.info(String.format(BANK_WITH_ID_NOT_FOUND, bankDto.getId()));
      return 0;
    }
  }

  @Override
  public int deleteById(Long id) {
    log.info("Delete bank by id = {}", id);
    return bankDao.deleteById(id);
  }

  /**
   * Print bank to PDF
   *
   * @param id - bank id
   * @return file generated path
   */
  @Override
  public String writeToPdf(Long id) {
    log.info("Print bank to PDF by id = {}", id);
    if (Objects.isNull(id) || id < 0) {
      throw new ServiceException("Bank id isn't correct");
    }
    Optional<Bank> optionalBank = bankDao.findById(id);
    if (optionalBank.isPresent()) {
      BankDto bankDto = BankMapper.INSTANCE.toBankDto(optionalBank.get());
      return bankPdfWriter.printToPdf(bankDto);
    } else {
      log.info(String.format(BankServiceImpl.BANK_WITH_ID_NOT_FOUND, id));
      throw new DaoNotFoundException(String.format(BANK_WITH_ID_NOT_FOUND, id));
    }
  }
}
