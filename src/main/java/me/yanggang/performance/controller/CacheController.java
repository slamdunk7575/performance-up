package me.yanggang.performance.controller;

import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CacheController {

    private final CacheManager cacheManager;
    private final RedisTemplate<String, String> redisTemplate;

    public CacheController(CacheManager cacheManager,
                           RedisTemplate<String, String> redisTemplate) {
        this.cacheManager = cacheManager;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/cache")
    public Object findAll() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        List<String> list = cacheManager.getCacheNames().stream().toList();

        String test = valueOperations.get("notice_all");

        List<Map<String, List<String>>> result = cacheManager.getCacheNames().stream()
                .map(cacheName -> {
                    Map<String, List<String>> entry = new HashMap<>();
                    // String value = valueOperations.get(cacheName + "::" + cacheName);
                    List<String> value = (List<String>) cacheManager.getCache(cacheName).get(cacheName).get();
                    entry.computeIfAbsent(cacheName, k -> new ArrayList<>()).addAll(value);
                    return entry;
                }).collect(Collectors.toList());

        return result;
    }
}
