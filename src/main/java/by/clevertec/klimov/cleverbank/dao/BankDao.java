package by.clevertec.klimov.cleverbank.dao;

import by.clevertec.klimov.cleverbank.entity.Bank;
import java.util.List;

public interface BankDao extends CrudDao<Bank, Long> {

  List<Bank> findAll();
}
