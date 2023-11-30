package by.clevertec.klimov.cleverbank.cache.factory.impl;

import by.clevertec.klimov.cleverbank.cache.Cache;
import by.clevertec.klimov.cleverbank.cache.CacheType;
import by.clevertec.klimov.cleverbank.cache.factory.CacheFactory;
import by.clevertec.klimov.cleverbank.cache.impl.LFUCache;
import by.clevertec.klimov.cleverbank.cache.impl.LRUCache;
import by.clevertec.klimov.cleverbank.exception.CacheException;

public class CacheFactoryImpl<K, V> implements CacheFactory<K, V> {

  @Override
  public Cache<K, V> getCache(CacheType cacheType) {
    if (cacheType.equals(CacheType.LFU)) {
      return new LFUCache<>();
    } else if (cacheType.equals(CacheType.LRU)) {
      return new LRUCache<>();
    } else {
      throw new CacheException("Cache not found");
    }
  }
}
