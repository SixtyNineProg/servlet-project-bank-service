package by.clevertec.klimov.cleverbank.app;

import by.clevertec.klimov.cleverbank.constants.Constants;
import by.clevertec.klimov.cleverbank.entity.Account;
import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.entity.User;
import by.clevertec.klimov.cleverbank.utils.CustomCsvToBean;
import java.io.IOException;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    CustomCsvToBean<Account> accountsCsvToBean = new CustomCsvToBean<>();
    List<Account> accounts =
        accountsCsvToBean.beanBuilder(Constants.PATH_ACCOUNTS_CSV, Account.class, ',');
    CustomCsvToBean<Bank> bankCustomCsvToBean = new CustomCsvToBean<>();
    List<Bank> banks = bankCustomCsvToBean.beanBuilder(Constants.PATH_BANKS_CSV, Bank.class, ',');
    CustomCsvToBean<User> userCustomCsvToBean = new CustomCsvToBean<>();
    List<User> users = userCustomCsvToBean.beanBuilder(Constants.PATH_USERS_CSV, User.class, ',');

    banks.forEach(bank -> bank.setUsers(users));

    users.forEach(
        user -> {
          user.setBank(banks);
          user.setAccount(accounts);
        });

    System.out.println(banks);
    System.out.println(users);
    System.out.println(accounts);
  }
}
