package com.subhankar.ratingservice.service;

import com.subhankar.ratingservice.model.DO.RatingDO;
import com.subhankar.ratingservice.model.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface RatingService {
    ResponseEntity<ResponseDTO> addRating(RatingDO ratingDO);
    ResponseEntity<ResponseDTO> getRatingByUserId(String userId);
    ResponseEntity<ResponseDTO> getRatingByHotelId(String productId);
    ResponseEntity<ResponseDTO> getRatingByUserIdAndHotelId(String userId, String productId);
    ResponseEntity<ResponseDTO> updateRatingByUserIdAndHotelId(String userId, String productId, RatingDO ratingDO);
    ResponseEntity<ResponseDTO> deleteRatingByUserIdAndHotelId(String userId, String productId);

}
