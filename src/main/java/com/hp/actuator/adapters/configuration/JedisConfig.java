package com.hp.actuator.adapters.configuration;

import com.hp.actuator.adapters.monitoring.interfaces.ClientRedisConfigurationCustom;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.resource.ClientResources;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


import java.time.Duration;


@Profile("local-redis")
@Configuration
//@EnableConfigurationProperties(RedisProperties.class)
public class JedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration clientConfiguration =
                LettuceClientConfiguration.builder()
                        .shutdownTimeout(Duration.ofSeconds(5))
                        .commandTimeout(Duration.ofSeconds(5))
                        .build();

        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();

        return new LettuceConnectionFactory(configuration, clientConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(this.redisConnectionFactory());
        template.afterPropertiesSet();
        return template;
    }
}
