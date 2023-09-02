package by.clevertec.klimov.cleverbank.entity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankTest {

  public static final double DEPOSIT_AMOUNT = 200.0;
  public static final double WITHDRAW_AMOUNT = 200.0;
  private Bank bankUnderTest;

  @BeforeEach
  void setUp() {
    bankUnderTest =
        new Bank(
            "Alfa",
            new ArrayList<>(
                Collections.singletonList(
                    User.builder()
                        .name("Mark")
                        .account(
                            Account.builder()
                                .balance(1000.0)
                                .transactions(new ArrayList<>())
                                .build())
                        .build())));
  }

  @Test
  void testAddUser_AddUser_UserListSizeIncrement() {
    final User user = User.builder().account(Account.builder().balance(0.0).build()).build();
    int actualUserListSize = bankUnderTest.getUsersSize();

    bankUnderTest.addUser(user);

    Assertions.assertEquals(++actualUserListSize, bankUnderTest.getUsersSize());
  }

  @Test
  void testDeposit_MakeDeposit_IncreaseAmount() {
    final User user = bankUnderTest.getUser("Mark").orElse(User.builder().build());
    double actualUserBalance = bankUnderTest.getUserBalance("Mark");

    bankUnderTest.deposit(user, DEPOSIT_AMOUNT);

    Assertions.assertEquals(
        actualUserBalance + DEPOSIT_AMOUNT, bankUnderTest.getUserBalance("Mark"));
  }

  @Test
  void testWithdraw_DoWithdraw_DecreaseBalance() {
    final User user = bankUnderTest.getUser("Mark").orElse(User.builder().build());
    double actualUserBalance = bankUnderTest.getUserBalance("Mark");

    bankUnderTest.withdraw(user, WITHDRAW_AMOUNT);

    Assertions.assertEquals(
        actualUserBalance - WITHDRAW_AMOUNT, bankUnderTest.getUserBalance("Mark"));
  }

  @Test
  void isExistUser_NonExistentUser_False() {
    final User user =
        User.builder().name("Dmitry").account(Account.builder().balance(0.0).build()).build();

    final boolean result = bankUnderTest.isExistUser(user);

    assertFalse(result);
  }

  @Test
  void isExistUser_ExistentUser_True() {

    final User user =
        User.builder()
            .name("Mark")
            .account(Account.builder().balance(1000.0).transactions(new ArrayList<>()).build())
            .build();

    final boolean result = bankUnderTest.isExistUser(user);

    assertTrue(result);
  }
}
