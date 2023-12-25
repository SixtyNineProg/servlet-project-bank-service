package by.clevertec.klimov.cleverbank.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.test_data.BankTestData;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class BankMapperTest {

  @Test
  void toBankDto_whenInputBank_thenValidBankDtoExpected() {
    // given
    Bank bank = BankTestData.builder().build().buildBank();
    BankDto expected = BankTestData.builder().build().buildBankDto();

    // when
    BankDto actual = BankMapper.INSTANCE.toBankDto(bank);

    // then
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void toBankDto_whenInputListBanks_thenValidListBanksDtoExpected() {
    // given
    List<Bank> banks = Collections.singletonList(BankTestData.builder().build().buildBank());
    List<BankDto> expected =
        Collections.singletonList(BankTestData.builder().build().buildBankDto());

    // when
    List<BankDto> actual = BankMapper.INSTANCE.toBankDto(banks);

    // then
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void merge_whenMergeBankDtoWithBank_thenValidBankExpected() {
    // given
    BankDto bankDto = BankTestData.builder().build().buildBankDto();
    bankDto.setId(null);
    bankDto.setName("Hogwarts");
    Bank bank = BankTestData.builder().build().buildBank();
    Bank expected = BankTestData.builder().build().buildBank();
    expected.setName(bankDto.getName());

    // when
    Bank actual = BankMapper.INSTANCE.merge(bankDto, bank);

    // then
    assertThat(actual).isEqualTo(expected);
  }
}
