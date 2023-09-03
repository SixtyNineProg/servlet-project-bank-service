package by.clevertec.klimov.cleverbank.app;

import by.clevertec.klimov.cleverbank.configuration.Configuration;
import by.clevertec.klimov.cleverbank.entity.Account;
import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.entity.User;
import by.clevertec.klimov.cleverbank.exception.ServiceException;
import by.clevertec.klimov.cleverbank.utils.CustomCsvToBean;
import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.yaml.snakeyaml.Yaml;

public class Main {

  public static final String PATH_FILE_CONFIGURATION = "src/main/resources/application.yml";

  public static void main(String[] args) throws IOException {

    Configuration configuration = loadConfig();
    System.out.println(configuration);

    CustomCsvToBean<Account> accountsCsvToBean = new CustomCsvToBean<>();
    List<Account> accounts =
        accountsCsvToBean.beanBuilder(configuration.getPaths().getAccounts(), Account.class, ',');
    CustomCsvToBean<Bank> bankCustomCsvToBean = new CustomCsvToBean<>();
    List<Bank> banks = bankCustomCsvToBean.beanBuilder(configuration.getPaths().getBanks(), Bank.class, ',');
    CustomCsvToBean<User> userCustomCsvToBean = new CustomCsvToBean<>();
    List<User> users = userCustomCsvToBean.beanBuilder(configuration.getPaths().getUsers(), User.class, ',');

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

  private static Configuration loadConfig() {
    Yaml yaml = new Yaml();
    File initialFile = new File(PATH_FILE_CONFIGURATION);
    InputStream inputStream;
    try {
      inputStream = new FileInputStream(initialFile);
    } catch (FileNotFoundException e) {
      throw new ServiceException(e);
    }
    Map<String, Object> data = yaml.load(inputStream);
    Gson gson = new Gson();
    Type typeObject = new TypeToken<HashMap<String, Object>>() {}.getType();
    String gsonData = gson.toJson(data, typeObject);
    return gson.fromJson(gsonData, Configuration.class);
  }
}
