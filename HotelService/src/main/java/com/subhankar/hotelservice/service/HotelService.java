package com.subhankar.hotelservice.service;

import com.subhankar.hotelservice.model.DO.HotelDO;
import com.subhankar.hotelservice.model.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface HotelService {
    ResponseEntity<ResponseDTO> addHotel(HotelDO hotelDO);
    ResponseEntity<ResponseDTO> getHotel(String id);
    ResponseEntity<ResponseDTO> getAllHotels();
    ResponseEntity<ResponseDTO> updateHotel(String id, HotelDO hotelDO);
    ResponseEntity<ResponseDTO> deleteHotel(String id);

}
