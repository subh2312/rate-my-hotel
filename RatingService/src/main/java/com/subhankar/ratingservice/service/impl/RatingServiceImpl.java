package com.subhankar.ratingservice.service.impl;

import com.subhankar.ratingservice.integration.model.UserDO;
import com.subhankar.ratingservice.integration.service.IntegrationService;
import com.subhankar.ratingservice.model.DO.RatingDO;
import com.subhankar.ratingservice.model.DTO.ResponseDTO;
import com.subhankar.ratingservice.repository.RatingRepository;
import com.subhankar.ratingservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final IntegrationService integrationService;
    @Override
    public ResponseEntity<ResponseDTO> addRating(RatingDO ratingDO) {
        return new ResponseEntity<>(ResponseDTO.builder().message("Rating added successfully").status("success").data(ratingRepository.save(ratingDO)).build(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDTO> getRatingByUserId(String userId) {
        return ratingRepository.findAllByUserId(userId).isEmpty() ? new ResponseEntity<>(ResponseDTO.builder().message("No rating found for user").status("failure").data(null).build(), HttpStatus.NOT_FOUND) : new ResponseEntity<>(ResponseDTO.builder().message("Rating found for user").status("success").data(ratingRepository.findAllByUserId(userId)).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> getRatingByHotelId(String productId) {
        List<RatingDO> ratingDOList = ratingRepository.findAllByHotelId(productId);
        if(ratingDOList.isEmpty())
            return new ResponseEntity<>(ResponseDTO.builder().message("No rating found for hotel").status("200").data(Collections.emptyList()).build(), HttpStatus.OK);
        for (RatingDO ratingDO : ratingDOList) {
            ResponseEntity<ResponseDTO> responseDTO = integrationService.getUser(ratingDO.getUserId());
            LinkedHashMap<String,String> responseData = (LinkedHashMap<String, String>) responseDTO.getBody().getData();
            String name = responseData.get("name");
            ratingDO.setUserId(name);
        }
        return new ResponseEntity<>(ResponseDTO.builder().message("Rating found for hotel").status("success").data(ratingDOList).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> getRatingByUserIdAndHotelId(String userId, String productId) {
        return ratingRepository.findAllByUserIdAndHotelId(userId, productId).isEmpty() ? new ResponseEntity<>(ResponseDTO.builder().message("No rating found for user and hotel").status("failure").data(null).build(), HttpStatus.NOT_FOUND) : new ResponseEntity<>(ResponseDTO.builder().message("Rating found for user and hotel").status("success").data(ratingRepository.findAllByUserIdAndHotelId(userId, productId)).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> updateRatingByUserIdAndHotelId(String userId, String productId, RatingDO ratingDO) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteRatingByUserIdAndHotelId(String userId, String productId) {
        return ratingRepository.findAllByUserIdAndHotelId(userId, productId).isEmpty() ? new ResponseEntity<>(ResponseDTO.builder().message("No rating found for user and hotel").status("failure").data(null).build(), HttpStatus.NOT_FOUND) : new ResponseEntity<>(ResponseDTO.builder().message("Rating deleted for user and hotel").status("success").data(null).build(), HttpStatus.OK);
    }
}
