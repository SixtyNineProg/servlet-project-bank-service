package by.clevertec.klimov.cleverbank.utils;

import static org.junit.jupiter.api.Assertions.*;

import by.clevertec.klimov.cleverbank.configuration.Configuration;
import by.clevertec.klimov.cleverbank.configuration.ConfigurationLoader;
import by.clevertec.klimov.cleverbank.entity.Account;
import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.entity.User;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class CustomCsvToBeanTest {

  private final CustomCsvToBean<Account> accountsCsvToBean = new CustomCsvToBean<>();

  @Test
  void beanBuilder() throws IOException {
    Configuration configuration = ConfigurationLoader.getConfiguration();
    Configuration.Paths paths = configuration.getPaths();
    List<Account> accounts = accountsCsvToBean.beanBuilder(paths.getAccounts(), Account.class, ',');
    CustomCsvToBean<Bank> bankCustomCsvToBean = new CustomCsvToBean<>();
    List<Bank> banks =
        bankCustomCsvToBean.beanBuilder(
            paths.getBanks(), by.clevertec.klimov.cleverbank.entity.Bank.class, ',');
    CustomCsvToBean<User> userCustomCsvToBean = new CustomCsvToBean<>();
    List<User> users = userCustomCsvToBean.beanBuilder(paths.getUsers(), User.class, ',');
    banks.forEach(bank -> bank.setUsers(users));
    users.forEach(
        user -> {
          user.setBank(banks);
          user.setAccount(accounts);
        });
  }
}
