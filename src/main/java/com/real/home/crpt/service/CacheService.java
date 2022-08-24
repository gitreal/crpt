package com.real.home.crpt.service;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.*;
import java.util.function.Function;

public class CacheService {

    private static final ConcurrentMap<Object, Future<Object>> concurrentMap = new ConcurrentHashMap<>();
    private static final AsyncLoadingCache<Object, Future<Object>> cache = Caffeine.newBuilder()
            .buildAsync((key, executor) -> CompletableFuture.supplyAsync(() -> concurrentMap.get(key)));

//    @Cacheable(value = "Results")
    public Future<Object> compute(Object key, Function<Object, Object> f)
            throws ExecutionException, InterruptedException {

        return cache.get(key).thenCompose(result -> {
            concurrentMap.put(key, CompletableFuture.completedFuture(f.apply(key)));
            return cache.get(key);
        }).get();

    }
}
