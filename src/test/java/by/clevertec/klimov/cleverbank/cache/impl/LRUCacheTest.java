package by.clevertec.klimov.cleverbank.cache.impl;

import static org.assertj.core.api.Assertions.assertThat;

import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.test_data.BankTestData;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class LRUCacheTest {

  @Test
  void put_whenPutBank_thenSizeCacheIncrement() {
    // given
    LRUCache<Long, Bank> cache = new LRUCache<>();
    Bank expected = BankTestData.builder().build().toBuilder().build().buildBank();
    int expectedSize = 1;

    // when
    cache.put(expected.getId(), expected);
    Bank actual = cache.get(expected.getId()).orElseThrow();

    // then
    assertThat(cache.size()).isEqualTo(expectedSize);
    assertThat(actual)
        .hasFieldOrPropertyWithValue(Bank.Fields.id, expected.getId())
        .hasFieldOrPropertyWithValue(Bank.Fields.name, expected.getName())
        .hasFieldOrPropertyWithValue(Bank.Fields.users, expected.getUsers());
  }

  @Test
  void put_whenPutTwoBanksInCacheWithOneCapacity_thenFirstBankWillBeForcedOut() {
    // given
    LRUCache<Long, Bank> cache = new LRUCache<>();
    cache.setCapacity(1);
    Bank bank = BankTestData.builder().build().toBuilder().build().buildBank();
    Bank bankTwo = BankTestData.builder().build().toBuilder().build().setId(2).buildBank();

    // when
    cache.put(bank.getId(), bank);
    cache.put(bankTwo.getId(), bankTwo);
    Optional<Bank> actual = cache.get(bank.getId());

    // then
    assertThat(actual).isEmpty();
  }

  @Test
  void clear_whenAddBankAndClear_thanExpectedEmptyCache() {
    // given
    LRUCache<Long, Bank> cache = new LRUCache<>();
    Bank bank = BankTestData.builder().build().toBuilder().build().buildBank();
    int expectedSize = 0;

    // when
    cache.put(bank.getId(), bank);
    cache.clear();

    // then
    assertThat(cache.size()).isEqualTo(expectedSize);
  }

  @Test
  void get_whenWorkWithBanks_thanLastRecentlyBankWillBeForcedOut() {
    // given
    LRUCache<Long, Bank> cache = new LRUCache<>();
    cache.setCapacity(2);
    Bank bank = BankTestData.builder().build().toBuilder().build().buildBank();
    Bank bankTwo = BankTestData.builder().build().setId(2).toBuilder().build().buildBank();
    Bank bankThree = BankTestData.builder().build().setId(3).toBuilder().build().buildBank();

    // when
    cache.put(bank.getId(), bank);
    cache.put(bankTwo.getId(), bankTwo);
    cache.get(bank.getId());
    cache.put(bankThree.getId(), bankThree);
    Optional<Bank> actual = cache.get(bankTwo.getId());

    // then
    assertThat(actual).isEmpty();
  }
}
