package com.amsidh.mvc.personws.service.impl;

import com.amsidh.mvc.personws.model.Address;
import com.amsidh.mvc.personws.model.Person;
import com.amsidh.mvc.personws.model.PersonResponseModel;
import com.amsidh.mvc.personws.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    private static final List<Person> persons = new ArrayList<>();
    private final RetryTemplate retryTemplate;
    private final RestTemplate restTemplate;
    private final Environment environment;
    private final ObjectMapper objectMapper;

    static {
        log.info("Loading sample data of persons");
        persons.addAll(Arrays.asList(
                Person.builder().personId(UUID.randomUUID().toString()).name("Amsidh Lokhande").age(38).build(),
                Person.builder().personId(UUID.randomUUID().toString()).name("Anjali Lokhande").age(34).build(),
                Person.builder().personId(UUID.randomUUID().toString()).name("Adithi Lokhande").age(11).build(),
                Person.builder().personId(UUID.randomUUID().toString()).name("Aditya Lokhande").age(8).build()
        ));
    }

    @Override
    public List<Person> getAllPersons() {
        log.info("Inside getAllPersons PersonServiceImpl");
        return persons;
    }

    @Override
    public Person createPerson(Person person) {
        log.info("Inside createPerson PersonServiceImpl");
        person.setPersonId(UUID.randomUUID().toString());
        persons.add(person);
        return person;
    }

    @Override
    public PersonResponseModel findByPersonId(String personId) {
        log.info("Inside findByPersonId PersonServiceImpl " + personId);
        Person person = persons.stream().filter(p -> Objects.equals(p.getPersonId(), personId)).findFirst().orElseThrow(() -> new RuntimeException(String.format("Person with personId %s not found.", personId)));
        PersonResponseModel personResponseModel = objectMapper.convertValue(person, PersonResponseModel.class);
        personResponseModel.setAddresses(getUserAddresses(personId));
        log.info(person.toString());
        return personResponseModel;
    }

    @Override
    public Person updatePerson(String personId, Person person) {
        log.info("Inside updatePerson PersonServiceImpl " + personId);
        return persons.stream().filter(p -> Objects.equals(p.getPersonId(), personId)).findFirst().map(oldPerson -> {
            Optional.ofNullable(person.getName()).ifPresent(oldPerson::setName);
            Optional.ofNullable(person.getAge()).ifPresent(oldPerson::setAge);
            Optional.ofNullable(person.getEmail()).ifPresent(oldPerson::setEmail);
            return oldPerson;
        }).orElseThrow(() -> new RuntimeException(String.format("Person with personId %s not found.", personId)));
    }

    @Override
    public void deletePersonByPersonId(String personId) {
        log.info("Inside deletePersonByPersonId PersonServiceImpl " + personId);
        persons.removeIf(person -> Objects.equals(person.getPersonId(), personId));
    }

    @Retryable
    public List<Address> getUserAddresses(String personId) {
        log.info("Inside getUserAddresses PersonServiceImpl " + personId);
        String addressUrl = environment.getProperty("address-ws.user.addresses");
        log.info("Fetching address of a person for personId " + personId);
        List<Address> addresses = retryTemplate.execute(retryContext -> {
            ResponseEntity<List<Address>> responseEntity = restTemplate.exchange(Objects.requireNonNull(addressUrl), HttpMethod.GET, null, new ParameterizedTypeReference<List<Address>>() {
            }, personId);
            return responseEntity.getStatusCode().is2xxSuccessful() ? responseEntity.getBody() : Collections.emptyList();
        }, retryContext -> {
            log.error("Looks like addresses-ws is down or not available so return default response");
            return Collections.emptyList();
        });
        log.info("Addresses Received from address-ws are " + addresses.toString());
        return addresses;
    }
}
