package com.eatitdog.eatitdog.global.config.cache;

import com.eatitdog.eatitdog.global.properties.RedisProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {

    private final RedisProperties redisProp;
    private static final int DEFAULT_EXPIRE_SECONDS = 86400; // 1 day

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisProp.getHost(), redisProp.getPort());
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

    @Bean(name = "cacheManager")
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofSeconds(DEFAULT_EXPIRE_SECONDS))
                .computePrefixWith(CacheKeyPrefix.simple())
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));

        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        cacheConfigurations.put("foodByNameCaching", RedisCacheConfiguration.defaultCacheConfig());
        cacheConfigurations.put("foodsBySearchCountCaching", RedisCacheConfiguration.defaultCacheConfig());
        cacheConfigurations.put("foodNamesByTypeCaching", RedisCacheConfiguration.defaultCacheConfig());
        cacheConfigurations.put("foodTypesCaching", RedisCacheConfiguration.defaultCacheConfig());
        cacheConfigurations.put("externalProductByProductNameCaching", RedisCacheConfiguration.defaultCacheConfig());
        cacheConfigurations.put("productListByFoodCaching", RedisCacheConfiguration.defaultCacheConfig());

        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(connectionFactory)
                .cacheDefaults(configuration)
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }
}
