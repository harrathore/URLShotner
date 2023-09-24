package com.example.UrlShortner.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash(value = "urls")
@AllArgsConstructor
@NoArgsConstructor
public class RedisUrls {
    @Id
    String longUrl;
    String shortUrl;
}
