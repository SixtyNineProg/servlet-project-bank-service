package by.clevertec.klimov.cleverbank.service;

import by.clevertec.klimov.cleverbank.dto.BankDto;
import java.util.List;

public interface BankService extends CrudService<BankDto, Long>, PfdService {

  List<BankDto> readAll();

  List<BankDto> read(Integer offset, Integer limit);
}
