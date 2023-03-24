package com.eatitdog.eatitdog.global.config.cache;

import net.sf.ehcache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import net.sf.ehcache.config.CacheConfiguration;

import java.util.Objects;

@Configuration
@EnableCaching
public class CacheConfig {

    private static final int EXPIRE_SECONDS = 86400; // 1 day

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setShared(true);
        return ehCacheManagerFactoryBean;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        CacheConfiguration dataCacheConfig = new CacheConfiguration()
                .eternal(false)
                .timeToIdleSeconds(0)
                .timeToLiveSeconds(EXPIRE_SECONDS)
                .maxEntriesLocalHeap(1000)
                .maxElementsOnDisk(1000)
                .memoryStoreEvictionPolicy("LRU")
                .name("foodByNameCaching");

        Cache dataCache = new Cache(dataCacheConfig);
        Objects.requireNonNull(ehCacheManagerFactoryBean().getObject())
                .addCache(dataCache);
        return new EhCacheCacheManager(
                Objects.requireNonNull(ehCacheManagerFactoryBean().getObject())
        );
    }
}
