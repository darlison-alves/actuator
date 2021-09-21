package com.hp.actuator.adapters.monitoring.interfaces;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.resource.ClientResources;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;

import java.time.Duration;
import java.util.Optional;

public class ClientRedisConfigurationCustom implements LettuceClientConfiguration {
    @Override
    public boolean isUseSsl() {
        return false;
    }

    @Override
    public boolean isVerifyPeer() {
        return false;
    }

    @Override
    public boolean isStartTls() {
        return false;
    }

    @Override
    public Optional<ClientResources> getClientResources() {
        return Optional.empty();
    }

    @Override
    public Optional<ClientOptions> getClientOptions() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getClientName() {
        return Optional.empty();
    }

    @Override
    public Optional<ReadFrom> getReadFrom() {
        return Optional.empty();
    }

    @Override
    public Duration getCommandTimeout() {
        return null;
    }

    @Override
    public Duration getShutdownTimeout() {
        return Duration.ofSeconds(5);
    }

    @Override
    public Duration getShutdownQuietPeriod() {
        return Duration.ofSeconds(5);
    }
}
