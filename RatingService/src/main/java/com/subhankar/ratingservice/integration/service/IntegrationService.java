package com.subhankar.ratingservice.integration.service;

import com.subhankar.ratingservice.model.DTO.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface IntegrationService {

    @GetMapping("/users/{id}")
    ResponseEntity<ResponseDTO> getUser(@PathVariable String id);
}
