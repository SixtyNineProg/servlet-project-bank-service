package by.clevertec.klimov.cleverbank.app;

import by.clevertec.klimov.cleverbank.configuration.Configuration;
import by.clevertec.klimov.cleverbank.configuration.ConfigurationLoader;
import by.clevertec.klimov.cleverbank.entity.Account;
import by.clevertec.klimov.cleverbank.entity.User;
import by.clevertec.klimov.cleverbank.utils.CustomCsvToBean;
import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

@Slf4j
public class Main {

  public static void main(String[] args) throws IOException {
    Configuration configuration = ConfigurationLoader.getConfiguration();
    log.info(configuration.toString());

    log.info("size: {}", findAllClassesUsingClassLoader("by.clevertec.klimov.cleverbank").size());

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

  public static Set<Class<?>> findAllClassesUsingClassLoader(String packageName) {
    Reflections reflections = new Reflections(packageName);
    Set<String> classes = reflections.getAll(Scanners.SubTypes);
    return classes.stream()
        .map(Main::getClass)
        .filter(Objects::nonNull)
        .collect(Collectors.toSet());
  }

  private static Class<?> getClass(String className) {
    try {
      return Class.forName(className);
    } catch (ClassNotFoundException e) {
      log.info("ClassNotFoundException: {}", className);
      return null;
    } catch (NoClassDefFoundError e) {
      log.info("NoClassDefFoundError: {}", className);
      return null;
    }
  }
}
