package com.example.UrlShortner.service.impl;

import com.example.UrlShortner.models.RedisCounter;
import com.example.UrlShortner.repository.RedisCounterRepository;
import com.example.UrlShortner.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RedisServiceImpl implements RedisService {

    private final RedisCounterRepository redisCounterRepository;
    @Override
    public String initRedisCounter() {
        RedisCounter redisCounter = new RedisCounter("counterKey", 1);
        redisCounterRepository.save(redisCounter);
        return "counterKey has been saved into the redis";
    }
}
