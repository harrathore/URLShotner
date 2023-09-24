package com.example.UrlShortner.service.impl;


import com.example.UrlShortner.models.RedisCounter;
import com.example.UrlShortner.models.URLMappingModel;
import com.example.UrlShortner.repository.RedisCounterRepository;
import com.example.UrlShortner.repository.URLMappingEntityRepository;
import com.example.UrlShortner.service.UrlGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CounterUrlGeneratorService implements UrlGeneratorService {

    private final RedisCounterRepository redisCounterRepository;

    private final URLMappingEntityRepository URLMappingEntityRepository;



    @Override
    public Map<String, Object> generateShortUrlFromLongUrl(String longUrl) {
        String shortUrl = this.generateLongAndShortAndSave(longUrl);
        Map<String, Object> responseMap = new HashMap<>();
        if(shortUrl.isEmpty()){
            return responseMap;
        }else{
            responseMap.put("shortUrl", shortUrl);
            return responseMap;
        }

//            Thread t1 = new Thread(()->{
//                for(int i=0; i<1000; i++){
//                    this.generateLongAndShortAndSave();
//                }
//            });

//            Thread t2 = new Thread(()->{
//                for(int i=0; i<10000; i++){
//                    this.generateLongAndShortAndSave();
//                }
//            });
//
//            Thread t3 = new Thread(()->{
//                for(int i=0; i<10000; i++){
//                    this.generateLongAndShortAndSave();
//                }
//            });
//
//            Thread t4 = new Thread(()->{
//                for(int i=0; i<10000; i++){
//                    this.generateLongAndShortAndSave();
//                }
//            });
//
//            Thread t5 = new Thread(()->{
//                for(int i=0; i<10000; i++){
//                    this.generateLongAndShortAndSave();
//                }
//            });
//
//            Thread t6 = new Thread(()->{
//                for(int i=0; i<10000; i++){
//                    this.generateLongAndShortAndSave();
//                }
//            });
//
             //t1.start();
//            t2.start();
//            t3.start();
//            t4.start();
//            t5.start();
//            t6.start();
//           System.out.println("Thread processing complete now I will sleep");
    }

    private String generateLongAndShortAndSave(String longUrl) {
        Integer cnt = this.getAndIncrementCounter();
        if(cnt != 0){
            String shortUrl = this.generateAndSetIntoDB(cnt);
            long createdAt = Instant.now().getEpochSecond();
            URLMappingModel urlMappingModel = new URLMappingModel(shortUrl, longUrl, createdAt, "Harvendra");
            URLMappingEntityRepository.save(urlMappingModel);
            return shortUrl;
        }
        return "";
    }

    private String generateAndSetIntoDB(Integer counter){
        String counterStr = String.valueOf(counter);

        String zeroStr = "000000";
        Integer counterSize = counterStr.length();
        String shortUrl = zeroStr.substring(0, 6-counterSize) + counterStr;

        return shortUrl;
    }

    @Synchronized
    private Integer getAndIncrementCounter() {
        Optional<RedisCounter> maybeResponseCount = redisCounterRepository.findById("counterKey");
        if(maybeResponseCount.isPresent()){
            RedisCounter redisCounter = maybeResponseCount.get();
            Integer cnt = redisCounter.getValue();
            redisCounter.setValue(cnt+1);
            redisCounterRepository.save(redisCounter);
            System.out.println("Counter before is : " + cnt);
            return cnt;
        }else{
            return 0;
        }
    }


    @Override
    public String getLongUrlFromShortUrl(String shortUrl) {
        Optional<URLMappingModel> res = URLMappingEntityRepository.findById(shortUrl);
        if(res.isPresent()){
            return res.get().getLongUrl();
        }else{
            return null;
        }
    }
}
