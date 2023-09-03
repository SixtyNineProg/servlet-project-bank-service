package by.clevertec.klimov.cleverbank.app.processor;

import by.clevertec.klimov.cleverbank.entity.Transaction;
import by.clevertec.klimov.cleverbank.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckProcessor {

  public static void printCheck(User sender, User receiver, Transaction transaction) {
    System.out.println("Банковский чек");
    System.out.println("Номер чека: " + transaction.getUuid());
    System.out.println("Дата: " + transaction.getDate());
    System.out.println("Тип транзакции: " + transaction.getType());
    System.out.println("Банк отправителя: " + sender.getBank().getName());
    System.out.println("Банк получателя: " + receiver.getBank().getName());
    System.out.println("Счет отправителя: " + sender.getAccount().getId());
    System.out.println("Счет получателя: " + receiver.getAccount().getId());
    System.out.println("Сумма: " + transaction.getAmount() + " BYN");
    System.out.println("---------------");
  }
}
