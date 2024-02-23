package com.subhankar.userservice.integration.service;

import com.subhankar.userservice.model.DTO.ResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Collections;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelIntegration {
    @GetMapping("/hotels/{id}")
    @CircuitBreaker(name = "hotelService", fallbackMethod = "getHotelByIdFallback")
    ResponseEntity<ResponseDTO> getHotelById(@PathVariable String id);

    default ResponseEntity<ResponseDTO> getHotelByIdFallback(String id, Throwable t) {
        return ResponseEntity.ok(ResponseDTO.builder().message("Hotel service is down").status("DOWN").data(Collections.EMPTY_LIST).build());
    }
}
