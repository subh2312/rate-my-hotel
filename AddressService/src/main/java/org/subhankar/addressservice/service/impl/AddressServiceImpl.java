package org.subhankar.addressservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.subhankar.addressservice.model.DO.AddressDO;
import org.subhankar.addressservice.model.DO.AddressType;
import org.subhankar.addressservice.model.DTO.ResponseDTO;
import org.subhankar.addressservice.repository.AddressRepository;
import org.subhankar.addressservice.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;


    @Override
    public ResponseEntity<ResponseDTO> addAddress(AddressDO addressDO) {
        return new ResponseEntity<>(ResponseDTO.builder().message("Address Created Successfully.").status("201").data(addressRepository.save(addressDO)).build(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDTO> getAddress(String addressId) {
        return addressRepository.findById(addressId).map(addressDO -> new ResponseEntity<>(ResponseDTO.builder().message("Address Found Successfully.").status("200").data(addressDO).build(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(ResponseDTO.builder().message("Address Not Found.").status("404").build(), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ResponseDTO> updateAddress(AddressDO addressDO) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteAddress(String addressId) {
        return addressRepository.findById(addressId).map(addressDO -> {
            addressRepository.deleteById(addressId);
            return new ResponseEntity<>(ResponseDTO.builder().message("Address Deleted Successfully.").status("200").build(), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(ResponseDTO.builder().message("Address Not Found.").status("404").build(), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ResponseDTO> getAddressByUser(String id) {
        return addressRepository.findAllByConsumer(id).isEmpty() ? new ResponseEntity<>(ResponseDTO.builder().message("Address Not Found.").status("404").build(), HttpStatus.NOT_FOUND) : new ResponseEntity<>(ResponseDTO.builder().message("Address Found Successfully.").status("200").data(addressRepository.findAllByConsumer(id)).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> getAddressByHotel(String id) {
        return addressRepository.findAllByConsumer(id).isEmpty() ? new ResponseEntity<>(ResponseDTO.builder().message("Address Not Found.").status("404").build(), HttpStatus.NOT_FOUND) : new ResponseEntity<>(ResponseDTO.builder().message("Address Found Successfully.").status("200").data(addressRepository.findAllByConsumer(id)).build(), HttpStatus.OK);
    }

}
