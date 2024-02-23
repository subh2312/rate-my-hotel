package org.subhankar.addressservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.subhankar.addressservice.model.DO.AddressDO;
import org.subhankar.addressservice.model.DTO.ResponseDTO;
import org.subhankar.addressservice.service.AddressService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addAddress(@RequestBody AddressDO addressDO) {
        return addressService.addAddress(addressDO);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<ResponseDTO> getAddress(@PathVariable String addressId) {
        return addressService.getAddress(addressId);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateAddress(@RequestBody AddressDO addressDO) {
        return addressService.updateAddress(addressDO);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<ResponseDTO> deleteAddress(@PathVariable String addressId) {
        return addressService.deleteAddress(addressId);
    }
}
