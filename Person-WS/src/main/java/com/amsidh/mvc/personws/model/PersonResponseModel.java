package com.amsidh.mvc.personws.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class PersonResponseModel {
    private String personId;
    private String name;
    private Integer age;
    private String email;
    private List<Address> addresses = new ArrayList<>();
}
