package com.amsidh.mvc.personws.controller;

import com.amsidh.mvc.personws.model.Person;
import com.amsidh.mvc.personws.model.PersonResponseModel;
import com.amsidh.mvc.personws.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        log.info("Inside getAllPersons of PersonController class");
        return ResponseEntity.status(HttpStatus.OK).body(personService.getAllPersons());
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        log.info("Inside createPerson of PersonController class");
        Person createdPerson = personService.createPerson(person);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{personId}").buildAndExpand(createdPerson.getPersonId()).toUri();
        return ResponseEntity.created(uri).body(createdPerson);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonResponseModel> getPersonById(@PathVariable("personId") String personId) {
        log.info("Inside getPersonById of PersonController class");
        return ResponseEntity.status(HttpStatus.FOUND).body(personService.findByPersonId(personId));
    }

    @PutMapping("/{personId}")
    public ResponseEntity<Person> updatePerson(@PathVariable("personId") String personId, @RequestBody Person person) {
        log.info("Inside createPerson of PersonController class");
        Person updatePerson = personService.updatePerson(personId, person);
        return ResponseEntity.accepted().body(updatePerson);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> deletePerson(@PathVariable("personId") String personId) {
        personService.deletePersonByPersonId(personId);
        return ResponseEntity.noContent().build();
    }
}
