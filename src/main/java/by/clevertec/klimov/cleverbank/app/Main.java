package by.clevertec.klimov.cleverbank.app;

import by.clevertec.klimov.cleverbank.configuration.Configuration;
import by.clevertec.klimov.cleverbank.configuration.ConfigurationLoader;
import by.clevertec.klimov.cleverbank.entity.Account;
import by.clevertec.klimov.cleverbank.entity.User;
import by.clevertec.klimov.cleverbank.utils.CustomCsvToBean;
import java.io.*;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

  public static void main(String[] args) throws IOException {
    Configuration configuration = ConfigurationLoader.getConfiguration();
    log.debug(configuration.toString());

    Configuration.Paths paths = configuration.getPaths();
    CustomCsvToBean<Account> accountsCsvToBean = new CustomCsvToBean<>();
    List<Account> accounts = accountsCsvToBean.beanBuilder(paths.getAccounts(), Account.class, ',');
    CustomCsvToBean<by.clevertec.klimov.cleverbank.entity.Bank> bankCustomCsvToBean =
        new CustomCsvToBean<>();
    List<by.clevertec.klimov.cleverbank.entity.Bank> banks =
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
