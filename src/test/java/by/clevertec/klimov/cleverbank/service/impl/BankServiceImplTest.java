package by.clevertec.klimov.cleverbank.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import by.clevertec.klimov.cleverbank.dao.impl.BankDaoImpl;
import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.pdf.impl.BankPdfWriter;
import by.clevertec.klimov.cleverbank.test_data.BankTestData;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BankServiceImplTest {

  @Mock private BankDaoImpl bankDao;

  @Mock private BankPdfWriter bankPdfWriter;

  @InjectMocks private BankServiceImpl bankService;

  @Captor private ArgumentCaptor<BankDto> bankDtoArgumentCaptor;

  @Test
  void writeToPdf() {
    // given
    BankDto expected = BankTestData.builder().withBalance(9999.9).build().buildBankDto();
    Optional<Bank> optionalBank = Optional.of(BankTestData.builder().withBalance(9999.9).build().buildBank());
    doReturn(optionalBank).when(bankDao).findById(optionalBank.get().getId());
    doReturn("c:\\Users\\Keks\\IdeaProjects\\").when(bankPdfWriter).printToPdf(expected);

    // when
    bankService.writeToPdf(expected.getId());

    // then
    verify(bankPdfWriter).printToPdf(bankDtoArgumentCaptor.capture());
    assertThat(bankDtoArgumentCaptor.getValue())
        .hasFieldOrPropertyWithValue(Bank.Fields.name, expected.getName())
        .hasFieldOrPropertyWithValue(Bank.Fields.id, expected.getId())
        .hasFieldOrPropertyWithValue(Bank.Fields.balance, expected.getBalance())
        .hasFieldOrPropertyWithValue(Bank.Fields.location, expected.getLocation())
        .hasFieldOrPropertyWithValue(Bank.Fields.accountNumber, expected.getAccountNumber());
  }
}
