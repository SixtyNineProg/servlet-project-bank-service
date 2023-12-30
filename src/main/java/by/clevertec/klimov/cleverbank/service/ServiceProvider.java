package by.clevertec.klimov.cleverbank.service;

import by.clevertec.klimov.cleverbank.dao.impl.BankDaoImpl;
import by.clevertec.klimov.cleverbank.pdf.impl.BankPdfWriter;
import by.clevertec.klimov.cleverbank.service.impl.BankServiceImpl;
import lombok.Getter;

public class ServiceProvider {

  @Getter private static final ServiceProvider instance = new ServiceProvider();

  @Getter
  private final BankService bankService =
      new BankServiceImpl(new BankDaoImpl(), new BankPdfWriter());
}
