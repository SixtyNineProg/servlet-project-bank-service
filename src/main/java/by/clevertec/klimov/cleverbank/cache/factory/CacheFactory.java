package by.clevertec.klimov.cleverbank.cache.factory;

import by.clevertec.klimov.cleverbank.cache.Cache;
import by.clevertec.klimov.cleverbank.cache.CacheType;

public interface CacheFactory<K, V> {

  Cache<K, V> getCache(CacheType cacheType);
}
