package com.subhankar.userservice.integration.service;

import com.subhankar.userservice.model.DTO.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelIntegration {
    @GetMapping("/hotels/{id}")
    ResponseEntity<ResponseDTO> getHotelById(@PathVariable String id);
}
