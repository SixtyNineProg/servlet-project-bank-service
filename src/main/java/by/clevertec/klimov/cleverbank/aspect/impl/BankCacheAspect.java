package by.clevertec.klimov.cleverbank.aspect.impl;

import by.clevertec.klimov.cleverbank.aspect.AspectCache;
import by.clevertec.klimov.cleverbank.cache.Cache;
import by.clevertec.klimov.cleverbank.cache.CacheType;
import by.clevertec.klimov.cleverbank.cache.factory.impl.CacheFactoryImpl;
import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.mapper.Mapper;
import by.clevertec.klimov.cleverbank.mapper.impl.BankMapper;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class BankCacheAspect implements AspectCache {

  public static final String FORMAT_CACHE_SIZE = "Cache size: {}";
  private final Cache<Long, Bank> cache;
  private final Mapper<Bank, BankDto> mapper = new BankMapper();

  public BankCacheAspect() {
    cache = new CacheFactoryImpl<Long, Bank>().getCache(CacheType.LRU);
  }

  @Override
  @Pointcut("@annotation(by.clevertec.klimov.cleverbank.aspect.annotation.CreateBank)")
  public void create() {}

  @Override
  @Pointcut("@annotation(by.clevertec.klimov.cleverbank.aspect.annotation.ReadBank)")
  public void read() {}

  @Override
  @Pointcut("@annotation(by.clevertec.klimov.cleverbank.aspect.annotation.UpdateBank)")
  public void update() {}

  @Override
  @Pointcut("@annotation(by.clevertec.klimov.cleverbank.aspect.annotation.DeleteBank)")
  public void delete() {}

  @Around("create()")
  public Object doCreateProfiling(ProceedingJoinPoint joinPoint) throws Throwable {
    Bank bank = (Bank) joinPoint.getArgs()[0];
    log.info("Aspect around create bank. {}", bank);
    long rowCount = (Long) joinPoint.proceed();
    if (rowCount == 1) {
      cache.put(bank.getId(), bank);
    }
    log.info(FORMAT_CACHE_SIZE, cache.size());
    return rowCount;
  }

  @Around(value = "delete()")
  public Object doDeleteProfiling(ProceedingJoinPoint joinPoint) throws Throwable {
    Long id = (Long) joinPoint.getArgs()[0];
    log.info("Aspect around delete bank with id = {}", id);
    int rowCount = (Integer) joinPoint.proceed();
    if (rowCount == 1) {
      cache.delete(id);
    }
    log.info(FORMAT_CACHE_SIZE, cache.size());
    return rowCount;
  }

  @SuppressWarnings("unchecked")
  @Around(value = "read()")
  public Object doReadProfiling(ProceedingJoinPoint joinPoint) throws Throwable {
    Long id = (Long) joinPoint.getArgs()[0];
    log.info("Aspect around read bank by id = {}", id);
    Optional<Bank> optionalBank = cache.get(id);
    if (optionalBank.isEmpty()) {
      optionalBank = (Optional<Bank>) joinPoint.proceed();
      if (optionalBank.isPresent()) {
        Bank bank = optionalBank.get();
        cache.put(bank.getId(), bank);
      }
    }
    log.info(FORMAT_CACHE_SIZE, cache.size());
    return optionalBank;
  }

  @Around(value = "update()")
  public Object doUpdateProfiling(ProceedingJoinPoint joinPoint) throws Throwable {
    Bank bank = (Bank) joinPoint.getArgs()[0];
    log.info("Aspect around update bank. {}", bank);
    int rowCount = (Integer) joinPoint.proceed();
    if (rowCount == 1) {
      cache.put(bank.getId(), bank);
    }
    log.info(FORMAT_CACHE_SIZE, cache.size());
    return rowCount;
  }
}
