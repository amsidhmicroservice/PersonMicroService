package com.amsidh.mvc.addressws.controller;

import com.amsidh.mvc.addressws.model.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/addresses")
public class AddressController {

    @GetMapping("/{personId}/person")
    public ResponseEntity<List<Address>> getAllAddressesByPersonId(@PathVariable(name = "personId") String personId) {
        log.info("Inside getAllAddressesByPersonId method of AddressController " + personId);
        return ResponseEntity.ok(Arrays.asList(
                Address.builder().addressId(UUID.randomUUID().toString()).personId(personId).city("Pune").state("MH").pinCode(412105L).build(),
                Address.builder().addressId(UUID.randomUUID().toString()).personId(personId).city("Mumbai").state("MH").pinCode(100008L).build(),
                Address.builder().addressId(UUID.randomUUID().toString()).personId(personId).city("Bijapur").state("KA").pinCode(586119L).build()
        ));
    }
}
