package com.subhankar.hotelservice.controller;

import com.subhankar.hotelservice.model.DO.HotelDO;
import com.subhankar.hotelservice.model.DTO.ResponseDTO;
import com.subhankar.hotelservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addHotel(@RequestBody HotelDO hotelDO) {
        return hotelService.addHotel(hotelDO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getHotel(@PathVariable String id) {
        return hotelService.getHotel(id);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateHotel(@PathVariable String id,@RequestBody HotelDO hotelDO) {
        return hotelService.updateHotel(id,hotelDO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteHotel(@PathVariable String id) {
        return hotelService.deleteHotel(id);
    }
}
