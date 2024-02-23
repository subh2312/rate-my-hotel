package org.subhankar.addressservice.service;

import org.subhankar.addressservice.model.DO.AddressDO;
import org.subhankar.addressservice.model.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AddressService {
    ResponseEntity<ResponseDTO> addAddress(AddressDO addressDO);
    ResponseEntity<ResponseDTO> getAddress(String addressId);
    ResponseEntity<ResponseDTO> updateAddress(AddressDO addressDO);
    ResponseEntity<ResponseDTO> deleteAddress(String addressId);
}
