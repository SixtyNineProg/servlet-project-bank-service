package by.clevertec.klimov.cleverbank.controller;

import by.clevertec.klimov.cleverbank.emum.TransactionType;
import by.clevertec.klimov.cleverbank.entity.Transaction;
import by.clevertec.klimov.cleverbank.entity.User;
import by.clevertec.klimov.cleverbank.exception.ServiceException;

import java.time.LocalDateTime;

public class BankProcessor {
  //TODO check users in bank
//  public void transfer(Transaction transaction, User sender, User receiver) {
//    if (isExistUser(sender) && isExistUser(receiver)) {
//      if (amount > 0) {
//        if (getUserBalance(sender.getName()) >= amount) {
//          Transaction.builder()
//                  .type(TransactionType.TRANSFER)
//                  .amount(amount)
//                  .date(LocalDateTime.now())
//                  .build());
//        } else {
//          throw new ServiceException("Insufficient funds");
//        }
//      } else {
//        throw new ServiceException("Amount must be greater than zero");
//      }
//    } else {
//      throw new ServiceException("User not found in the bank");
//    }
//  }
}
