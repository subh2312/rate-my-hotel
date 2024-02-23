package com.subhankar.userservice.integration.service;

import com.subhankar.userservice.model.DTO.ResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;

@FeignClient(name="RATING-SERVICE")
public interface RatingIntegration {
    @GetMapping("/ratings/user/{id}")
    @CircuitBreaker(name = "ratingService", fallbackMethod = "getRatingByUserIdFallback")
    ResponseEntity<ResponseDTO> getRatingByUserId(@PathVariable String id);

    default ResponseEntity<ResponseDTO> getRatingByUserIdFallback(String id, Throwable t) {
        return ResponseEntity.ok(ResponseDTO.builder().message("Rating service is down").status("DOWN").data(Collections.EMPTY_LIST).build());
    }

}
