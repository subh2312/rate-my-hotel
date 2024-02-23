package com.subhankar.userservice.service.impl;

import com.subhankar.userservice.integration.model.AddressDO;
import com.subhankar.userservice.integration.model.AddressType;
import com.subhankar.userservice.integration.model.RatingDO;
import com.subhankar.userservice.integration.service.AddressIntegration;
import com.subhankar.userservice.integration.service.HotelIntegration;
import com.subhankar.userservice.integration.service.RatingIntegration;
import com.subhankar.userservice.model.DO.UserDO;
import com.subhankar.userservice.model.DTO.ResponseDTO;
import com.subhankar.userservice.repository.UserRepository;
import com.subhankar.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressIntegration addressIntegration;
    private final HotelIntegration hotelIntegration;
    private final RatingIntegration ratingIntegration;
    @Override
    public ResponseEntity<ResponseDTO> saveUser(UserDO userDO) {
        return new ResponseEntity<>(new ResponseDTO("User saved successfully","201",userRepository.save(userDO)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDTO> getUser(String id) {
        UserDO userDO = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        ResponseEntity<ResponseDTO> response = addressIntegration.getAddressByUserId(id);
        List<LinkedHashMap<String,String>> addressList = (List<LinkedHashMap<String,String>>) response.getBody().getData();
        List<AddressDO> addressDOList = new ArrayList<>();
        for (LinkedHashMap<String,String> address : addressList) {
            addressDOList.add(AddressDO.builder()
                    .id(address.get("id"))
                    .consumer(address.get("consumer"))
                    .addressType(AddressType.valueOf(address.get("addressType")))
                    .addressLine1(address.get("addressLine1"))
                    .addressLine2(address.get("addressLine2"))
                    .city(address.get("city"))
                    .state(address.get("state"))
                    .country(address.get("country"))
                    .zipCode(address.get("zipCode"))
                    .build());
        }
        userDO.setAddresses(addressDOList);
        return new ResponseEntity<>(new ResponseDTO("User fetched successfully","200",userDO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> updateUser(String id, UserDO userDO) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteUser(String id) {

        return userRepository.findById(id).map(userDO -> {
            userRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseDTO("User deleted successfully","200",null), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(new ResponseDTO("User not found","404",null), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ResponseDTO> getAllUsers() {
        return new ResponseEntity<>(new ResponseDTO("Users fetched successfully","200",userRepository.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> getUserRating(String id) {
        List<RatingDO> ratingsList = new ArrayList<>();
        ResponseEntity<ResponseDTO> ratingResponse = ratingIntegration.getRatingByUserId(id);
        List<LinkedHashMap<String,Object>> ratingDOList = (List<LinkedHashMap<String,Object>>) ratingResponse.getBody().getData();
        if(ratingDOList.isEmpty())
            return new ResponseEntity<>(new ResponseDTO("No rating found for user","404",null), HttpStatus.NOT_FOUND);
        for(LinkedHashMap<String,Object> ratingDO : ratingDOList){
            ResponseEntity<ResponseDTO> hotelResponse = hotelIntegration.getHotelById((String) ratingDO.get("hotelId"));
            LinkedHashMap<String,String> hotelMap = (LinkedHashMap<String, String>) hotelResponse.getBody().getData();
            ratingsList.add(RatingDO.builder()
                            .id((String) ratingDO.get("id"))
                            .userId((String) ratingDO.get("userId"))
                            .hotelId(hotelMap.get("name"))
                            .rating((Integer) ratingDO.get("rating"))
                            .comment((String) ratingDO.get("comment"))
                    .build());
        }
        return ratingsList.isEmpty() ? new ResponseEntity<>(new ResponseDTO("No rating found for user","404",null), HttpStatus.NOT_FOUND) : new ResponseEntity<>(new ResponseDTO("Rating found for user","200",ratingsList), HttpStatus.OK);
    }
}
