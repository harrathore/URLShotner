package com.example.UrlShortner.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "urlTable")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class URLMappingModel {

    @Id
    private String shortUrl;

    private String longUrl;
    private Long createdAt;
    private String createdBy;

}
