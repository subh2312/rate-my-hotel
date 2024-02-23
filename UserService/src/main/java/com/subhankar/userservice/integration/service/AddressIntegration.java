package com.subhankar.userservice.integration.service;

import com.subhankar.userservice.model.DTO.ResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;

@FeignClient(name="ADDRESS-SERVICE")
public interface AddressIntegration {
    @GetMapping("/addresses/user/{id}")
    @CircuitBreaker(name = "addressService", fallbackMethod = "getAddressByUserIdFallback")
    ResponseEntity<ResponseDTO> getAddressByUserId(@PathVariable String id);

    default ResponseEntity<ResponseDTO> getAddressByUserIdFallback(String id, Throwable t) {
        return ResponseEntity.ok(ResponseDTO.builder().message("Address service is down").status("DOWN").data(Collections.EMPTY_LIST).build());
    }
}
