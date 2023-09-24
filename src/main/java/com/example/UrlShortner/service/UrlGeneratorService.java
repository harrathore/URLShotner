package com.example.UrlShortner.service;

import com.example.UrlShortner.models.URLMappingModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UrlGeneratorService {
     Map<String, Object> generateShortUrlFromLongUrl(String longUrl);

     String getLongUrlFromShortUrl(String shortUrl);
}
