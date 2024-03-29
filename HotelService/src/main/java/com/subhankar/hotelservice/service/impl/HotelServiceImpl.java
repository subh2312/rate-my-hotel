package com.subhankar.hotelservice.service.impl;

import com.subhankar.hotelservice.integration.RatingService.model.RatingDO;
import com.subhankar.hotelservice.integration.RatingService.service.RatingIntegration;
import com.subhankar.hotelservice.model.DO.HotelDO;
import com.subhankar.hotelservice.model.DTO.ResponseDTO;
import com.subhankar.hotelservice.repository.HotelRepository;
import com.subhankar.hotelservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final RatingIntegration ratingIntegration;

    @Override
    public ResponseEntity<ResponseDTO> addHotel(HotelDO hotelDO) {
        return new ResponseEntity<>(ResponseDTO.builder().message("Hotel added successfully").data(hotelRepository.save(hotelDO)).status("201").build(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDTO> getHotel(String id) {
        ResponseEntity<ResponseDTO> responseEntity = ratingIntegration.getRatingByHotelId(id);

        HotelDO hotelDO = hotelRepository.findById(id).orElseThrow(()->new RuntimeException("Hotel not found"));
        if(responseEntity.getStatusCode() == HttpStatus.NOT_FOUND){
            return new ResponseEntity<>(ResponseDTO.builder().message("Hotel found").data(hotelDO).status("200").build(), HttpStatus.OK);
        }
        hotelDO.setRatings((List<RatingDO>) responseEntity.getBody().getData());
        return new ResponseEntity<>(ResponseDTO.builder().message("Hotel found").data(hotelDO).status("200").build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> getAllHotels() {
        return new ResponseEntity<>(ResponseDTO.builder().message("Hotels found").data(hotelRepository.findAll()).status("200").build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> updateHotel(String id, HotelDO hotelDO) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteHotel(String id) {
        return hotelRepository.findById(id).map(hotelDO -> {
            hotelRepository.delete(hotelDO);
            return new ResponseEntity<>(ResponseDTO.builder().message("Hotel deleted").status("200").build(), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(ResponseDTO.builder().message("Hotel not found").status("404").build(), HttpStatus.NOT_FOUND));
    }
}
