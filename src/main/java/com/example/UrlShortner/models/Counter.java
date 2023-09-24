package com.example.UrlShortner.models;


import org.springframework.stereotype.Component;

@Component
public class Counter {
    private Integer counter;

    public Counter(){
        counter = 1;
    }

    public Integer getAndUpdateCounter(){
        synchronized (Counter.class){
            counter += 1;
            return counter-1;
        }
    }

    public Integer getFinalCounter() {
        return counter;
    }
}
