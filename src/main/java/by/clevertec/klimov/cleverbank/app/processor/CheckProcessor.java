package by.clevertec.klimov.cleverbank.app.processor;

import by.clevertec.klimov.cleverbank.entity.Transaction;
import by.clevertec.klimov.cleverbank.entity.User;
import by.clevertec.klimov.cleverbank.exception.ServiceException;
import java.io.FileWriter;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckProcessor {

  public static void printCheck(User sender, User receiver, Transaction transaction) {
    try (FileWriter writer = new FileWriter("check/bank_check.txt")) {
      writer.write("Bank check\n");
      writer.write("Check number: " + transaction.getUuid() + "\n");
      writer.write("Date: " + transaction.getDate() + "\n");
      writer.write("Sender Bank: " + sender.getBank().getName() + "\n");
      writer.write("Receiver's bank: " + receiver.getBank().getName() + "\n");
      writer.write("Sender account: " + sender.getAccount().getId() + "\n");
      writer.write("Receiver's account: " + receiver.getAccount().getId() + "\n");
      writer.write("Amount: " + transaction.getAmount() + " BYN." + "\n");
    } catch (IOException e) {
      throw new ServiceException(e);
    }
  }
}
