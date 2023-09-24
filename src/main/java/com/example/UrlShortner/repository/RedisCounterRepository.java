package com.example.UrlShortner.repository;

import com.example.UrlShortner.models.RedisCounter;
import org.springframework.data.keyvalue.repository.KeyValueRepository;


public interface RedisCounterRepository extends KeyValueRepository<RedisCounter,String> {
}
