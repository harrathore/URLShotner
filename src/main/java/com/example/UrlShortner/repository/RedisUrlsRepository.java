package com.example.UrlShortner.repository;

import com.example.UrlShortner.models.RedisUrls;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface RedisUrlsRepository extends KeyValueRepository<RedisUrls,String> {
}
