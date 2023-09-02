package by.clevertec.klimov.cleverbank.entity;

import by.clevertec.klimov.cleverbank.emum.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AccountTest {

  public static final double DEPOSIT_AMOUNT = 200.0;
  public static final double WITHDRAW_AMOUNT = 200.0;
  private Account accountUnderTest;

  @BeforeEach
  void setUp() {
    accountUnderTest =
        new Account(
            0L,
            1000.0,
            new ArrayList<>(List.of(Transaction.builder().type(TransactionType.DEPOSIT).amount(0.0).build())));
  }

  @Test
  void testAddTransaction_AddTransaction_UserListSizeIncrement() {
    final Transaction transaction =
        Transaction.builder().type(TransactionType.DEPOSIT).amount(0.0).build();

    int transactionsSize = accountUnderTest.getTransactionsSize();

    accountUnderTest.addTransaction(transaction);

    Assertions.assertEquals(++transactionsSize, accountUnderTest.getTransactionsSize());
  }

  @Test
  void testHandleTransaction_DoDeposit_IncreaseAmount() {
    // Setup
    final Transaction transaction =
        Transaction.builder().type(TransactionType.DEPOSIT).amount(DEPOSIT_AMOUNT).build();

    double startBalance = accountUnderTest.getBalance();

    accountUnderTest.handleTransaction(transaction);

    // Verify the results
    Assertions.assertEquals(startBalance + DEPOSIT_AMOUNT, accountUnderTest.getBalance());
  }

  @Test
  void testHandleTransaction_DoWithdraw_DecreaseBalance() {
    // Setup
    final Transaction transaction =
            Transaction.builder().type(TransactionType.WITHDRAWAL).amount(WITHDRAW_AMOUNT).build();

    double startBalance = accountUnderTest.getBalance();

    accountUnderTest.handleTransaction(transaction);

    // Verify the results
    Assertions.assertEquals(startBalance - WITHDRAW_AMOUNT, accountUnderTest.getBalance());
  }
}
