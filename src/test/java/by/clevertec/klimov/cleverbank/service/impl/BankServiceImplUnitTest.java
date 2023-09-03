package by.clevertec.klimov.cleverbank.service.impl;

import static org.mockito.BDDMockito.given;

import by.clevertec.klimov.cleverbank.entity.Bank;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BankServiceImplUnitTest {

  private final Bank createTestBank =
      Bank.builder().name("Alfa").users(Collections.emptyList()).build();

  @Mock private BankServiceImpl bankServiceImplMock;

  @Test
  void testCreate_() {
    given(bankServiceImplMock.create(createTestBank)).willReturn(1);
    Assertions.assertTrue(bankServiceImplMock.create(createTestBank) > 0, "Bank isn't created");
  }
}
