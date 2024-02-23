package com.subhankar.ratingservice.controller;

import com.subhankar.ratingservice.model.DO.RatingDO;
import com.subhankar.ratingservice.model.DTO.ResponseDTO;
import com.subhankar.ratingservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addRating(@RequestBody RatingDO ratingDO) {
        return ratingService.addRating(ratingDO);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseDTO> getRatingByUserId(@PathVariable String id) {
        return ratingService.getRatingByUserId(id);
    }

    @GetMapping("/hotel/{id}")
    public ResponseEntity<ResponseDTO> getRatingByHotelId(@PathVariable String id) {
        return ratingService.getRatingByHotelId(id);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> getRatingByUserIdAndHotelId(@RequestParam String userId, @RequestParam String hotelId) {
        return ratingService.getRatingByUserIdAndHotelId(userId, hotelId);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateRatingByUserIdAndHotelId(@RequestParam String userId, @RequestParam String hotelId,@RequestBody RatingDO ratingDO) {
        return ratingService.updateRatingByUserIdAndHotelId(userId, hotelId, ratingDO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteRatingByUserIdAndHotelId(@RequestParam String userId, @RequestParam String hotelId) {
        return ratingService.deleteRatingByUserIdAndHotelId(userId, hotelId);
    }



}
