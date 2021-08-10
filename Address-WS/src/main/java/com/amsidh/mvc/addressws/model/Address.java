package com.amsidh.mvc.addressws.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Address {
    private String addressId;
    private String city;
    private String state;
    private Long pinCode;
    private String personId;
}
