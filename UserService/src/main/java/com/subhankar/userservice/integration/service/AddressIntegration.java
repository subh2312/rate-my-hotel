package com.subhankar.userservice.integration.service;

import com.subhankar.userservice.model.DTO.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ADDRESS-SERVICE")
public interface AddressIntegration {
    @GetMapping("/addresses/user/{id}")
    ResponseEntity<ResponseDTO> getAddressByUserId(@PathVariable String id);
}
