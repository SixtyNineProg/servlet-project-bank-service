package by.clevertec.klimov.cleverbank.aspect.impl;

import by.clevertec.klimov.cleverbank.aspect.AspectCache;
import by.clevertec.klimov.cleverbank.cache.Cache;
import by.clevertec.klimov.cleverbank.cache.CacheType;
import by.clevertec.klimov.cleverbank.cache.factory.impl.CacheFactoryImpl;
import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.mapper.Mapper;
import by.clevertec.klimov.cleverbank.mapper.impl.BankMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class BankCacheAspect implements AspectCache {

  private final Cache<Long, BankDto> cache;
  private final Mapper<Bank, BankDto> mapper = new BankMapper();

  public BankCacheAspect() {
    cache = new CacheFactoryImpl<Long, BankDto>().getCache(CacheType.LRU);
    cache.setCapacity(5);
  }

  //  @Pointcut("execution(*
  // by.clevertec.klimov.cleverbank.service.impl.BankServiceImpl.create(*))")

  @Override
  @Pointcut("@annotation(by.clevertec.klimov.cleverbank.aspect.annotation.CreateBank)")
  public void create() {}

  @Override
  @Pointcut("@annotation(by.clevertec.klimov.cleverbank.aspect.annotation.ReadBank)")
  public void readById() {}

  @Override
  @Pointcut("@annotation(by.clevertec.klimov.cleverbank.aspect.annotation.UpdateBank)")
  public void update() {}

  @Override
  @Pointcut("@annotation(by.clevertec.klimov.cleverbank.aspect.annotation.DeleteBank)")
  public void deleteById() {}

  @Around("create()")
  public Object beforeAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("Aspect around create bank");
    long rowCount = (Long) joinPoint.proceed();
    BankDto bankDto = (BankDto) joinPoint.getArgs()[0];
    cache.put(bankDto.getId(), bankDto);
    log.info("Cache size: {}", cache.size());
    return rowCount;
  }

  @Around(value = "deleteById()")
  public Object doDeleteProfiling(ProceedingJoinPoint joinPoint) throws Throwable {
    int rowCount = (Integer) joinPoint.proceed();
    cache.size();
    return rowCount;
  }
}
