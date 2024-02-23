package com.subhankar.hotelservice.integration.RatingService.service;

import com.subhankar.hotelservice.model.DTO.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RATING-SERVICE")
public interface RatingIntegration {
    @GetMapping("/ratings/hotel/{hotelId}")
    ResponseEntity<ResponseDTO> getRatingByHotelId(@PathVariable String hotelId);
}
