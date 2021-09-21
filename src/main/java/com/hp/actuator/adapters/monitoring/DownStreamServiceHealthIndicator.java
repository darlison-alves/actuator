package com.hp.actuator.adapters.monitoring;

import com.hp.actuator.application.services.RedisStateService;
import io.lettuce.core.RedisCommandTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class DownStreamServiceHealthIndicator implements ReactiveHealthIndicator, HealthContributor {

    @Autowired
    private RedisStateService redisStateService;

    @Override
    public Mono<Health> health() {
        return this.checkDownStreamServiceHealth().onErrorResume(
                ex -> Mono.just(new Health.Builder().down(ex).withDetail("message", ex.getMessage()).build()).timeout(Duration.ofSeconds(5))
        ) ;
    }

    private Mono<Health> checkDownStreamServiceHealth() {
        try {
            this.redisStateService.checkPing();
        } catch (RedisCommandTimeoutException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception exception) {
            exception.printStackTrace();
            return Mono.just(new Health.Builder().down(exception).build() );
        }
        return Mono.just(new Health.Builder().up().build() );
    }


//    @Override
//    public Mono<Health> health() {
//        System.out.println("HealthIndicator");
//        return Mono.just(this.healthIndicator.health());
//    }



}
