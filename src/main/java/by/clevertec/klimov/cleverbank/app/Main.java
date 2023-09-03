package by.clevertec.klimov.cleverbank.app;

import by.clevertec.klimov.cleverbank.app.processor.BankProcessor;
import by.clevertec.klimov.cleverbank.app.processor.BankProcessorImpl;
import by.clevertec.klimov.cleverbank.configuration.Configuration;
import by.clevertec.klimov.cleverbank.configuration.ConfigurationLoader;
import by.clevertec.klimov.cleverbank.entity.Account;
import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.entity.User;
import by.clevertec.klimov.cleverbank.utils.CustomCsvToBean;
import java.io.*;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {
    Configuration configuration = ConfigurationLoader.getConfiguration();
    System.out.println(configuration);

    Configuration.Paths paths = configuration.getPaths();
    CustomCsvToBean<Account> accountsCsvToBean = new CustomCsvToBean<>();
    List<Account> accounts =
        accountsCsvToBean.beanBuilder(paths.getAccounts(), Account.class, ',');
    CustomCsvToBean<Bank> bankCustomCsvToBean = new CustomCsvToBean<>();
    List<Bank> banks = bankCustomCsvToBean.beanBuilder(paths.getBanks(), Bank.class, ',');
    CustomCsvToBean<User> userCustomCsvToBean = new CustomCsvToBean<>();
    List<User> users = userCustomCsvToBean.beanBuilder(paths.getUsers(), User.class, ',');
    banks.forEach(bank -> bank.setUsers(users));
    users.forEach(
        user -> {
          user.setBank(banks);
          user.setAccount(accounts);
        });
    System.out.println(banks);
    System.out.println(users);
    System.out.println(accounts);

    User sender = users.get(0);
    User receiver = users.get(1);
    System.out.println(sender);
    System.out.println(receiver);
    BankProcessor bankProcessor = new BankProcessorImpl();
    bankProcessor.transfer(sender, receiver, 100);
    System.out.println(sender);
    System.out.println(receiver);

//    InterestCalculator calculator = new InterestCalculator(configuration.getInterestRatePercent(), users);
//    calculator.startInterestCalculation();
//    Thread.sleep(60000); // Выполнять в течение 60 секунд (120 раз)
//    calculator.stopInterestCalculation();
//    System.out.println(users);

  }
}
