package com.amsidh.mvc.personws.service;

import com.amsidh.mvc.personws.model.Person;
import com.amsidh.mvc.personws.model.PersonResponseModel;

import java.util.List;

public interface PersonService {
    List<Person> getAllPersons();

    Person createPerson(Person person);

    PersonResponseModel findByPersonId(String personId);

    Person updatePerson(String personId, Person person);

    void deletePersonByPersonId(String personId);
}
