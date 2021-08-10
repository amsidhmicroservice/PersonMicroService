package com.amsidh.mvc.personws.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Person {
  private String personId;
  private String name;
  private Integer age;
  private String email;
}
