package com.example.UrlShortner.controller;

import com.example.UrlShortner.service.UrlGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/url")
public class UrlController{

    @Autowired
    private UrlGeneratorService urlGeneratorService;

    @GetMapping("/generate")
    public ResponseEntity<Map> generateUrl(@RequestParam(name = "longUrl", required = true) String longUrl){
        Map<String, Object> responseMap = new HashMap<>();
        try{
            responseMap = urlGeneratorService.generateShortUrlFromLongUrl(longUrl);
            responseMap.put("message", "ShortUrl Generated successfully !");
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        }catch (Exception e){
            responseMap.put("message", "There is some problem !");
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/long")
    public ResponseEntity<String> getShortUrl(@RequestParam(name = "shortUrl", required = true) String shortUrl){
       try{
           String targetUrl = urlGeneratorService.getLongUrlFromShortUrl(shortUrl);
           HttpHeaders headers = new HttpHeaders();
           headers.add("Location", targetUrl);
           return new ResponseEntity<>("You have redirected to original url", headers, HttpStatus.TEMPORARY_REDIRECT);
       }catch (Exception e){
           return new ResponseEntity<>("There is some problem", HttpStatus.BAD_REQUEST);
       }
    }

}
