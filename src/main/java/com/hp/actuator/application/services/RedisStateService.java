package com.hp.actuator.application.services;

import com.hp.actuator.adapters.outBound.extenalAPI.RequestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class RedisStateService {

    @Autowired
    private RedisTemplate redisTemplate;

    public void checkPing() throws IOException {
//        this.redisTemplate.getConnectionFactory().getConnection().getSentinelConnection().isOpen();
        String ping = this.redisTemplate.getConnectionFactory().getConnection().ping();
        System.out.println(ping);
    }

    public void pingExternalService() throws IOException {
        RedisStateService.ping("localhost:6379");
    }

    public static void ping(String host) throws IOException {
        if(InetAddress.getByName(host).isReachable(5000))
            System.out.println("Ping OK: " + host);
    }
}
