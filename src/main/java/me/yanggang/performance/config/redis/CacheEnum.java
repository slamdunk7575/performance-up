package me.yanggang.performance.config.redis;

import lombok.Getter;

import static java.util.concurrent.TimeUnit.SECONDS;

@Getter
public enum CacheEnum {

    NOTICE_ALL("notice_all", SECONDS.toSeconds(10))
    ;

    private final String cacheName;
    private final long expireSecond;

    CacheEnum(String cacheName, long expireSecond) {
        this.cacheName = cacheName;
        this.expireSecond = expireSecond;
    }
}
