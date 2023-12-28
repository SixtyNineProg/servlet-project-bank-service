package by.clevertec.klimov.cleverbank.service;

import by.clevertec.klimov.cleverbank.service.impl.BankServiceImpl;
import lombok.Getter;

public class ServiceProvider {

  @Getter private static final ServiceProvider instance = new ServiceProvider();

  @Getter private final BankService bankService = new BankServiceImpl();
}
