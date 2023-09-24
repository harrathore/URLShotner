package com.example.UrlShortner.repository;

import com.example.UrlShortner.models.URLMappingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLMappingEntityRepository extends JpaRepository<URLMappingModel, String> {
}

