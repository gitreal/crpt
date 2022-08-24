package com.real.home.crpt.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.*;

@Configuration
@EnableCaching
public class SpringCacheConfig {

    @Bean
    public CacheManager cacheManager() {
        Collection<CaffeineCache> caches = Collections.singletonList(buildCache(resultCacheBuilder()));
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    private CaffeineCache buildCache(Caffeine<Object, Object> cacheBuilder) {
        return new CaffeineCache("Results", cacheBuilder.build());
    }

    @Bean
    protected Caffeine<Object, Object> resultCacheBuilder() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        return Caffeine.newBuilder()
                .maximumSize(100)
                .executor(executorService);
    }
}
