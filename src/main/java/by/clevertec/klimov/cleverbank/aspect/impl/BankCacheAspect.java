package by.clevertec.klimov.cleverbank.aspect.impl;

import by.clevertec.klimov.cleverbank.aspect.AspectCache;
import by.clevertec.klimov.cleverbank.cache.Cache;
import by.clevertec.klimov.cleverbank.cache.factory.impl.CacheFactoryImpl;
import by.clevertec.klimov.cleverbank.configuration.ConfigurationLoader;
import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.mapper.Mapper;
import by.clevertec.klimov.cleverbank.mapper.impl.BankMapper;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class BankCacheAspect implements AspectCache {

  private final Cache<Long, Bank> cache;
  private final Mapper<Bank, BankDto> mapper = new BankMapper();

  public BankCacheAspect() {
    cache =
        new CacheFactoryImpl<Long, Bank>()
            .getCache(ConfigurationLoader.getConfiguration().getCache().getType());
    cache.setCapacity(ConfigurationLoader.getConfiguration().getCache().getSize());
  }

  @Override
  public void create() {}

  @Override
  public void readById() {}

  @Override
  public void update() {}

  @Override
  public void deleteById() {}
}
