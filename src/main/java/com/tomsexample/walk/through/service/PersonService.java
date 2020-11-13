package com.tomsexample.walk.through.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.tomsexample.walk.through.dao.PersonDao;
import com.tomsexample.walk.through.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public void addPerson(Person person){
        personDao.insertPerson(person);
    }

    public List<Person> getAllPeople(){
        return personDao.getAllPeople();
    }

    public Optional<Person> selectPersonById(UUID id){
        return personDao.selectPersonById(id);
    }

    public void deletePersonById(UUID id) {
        personDao.deletePersonById(id);
    }

    public void updatePersonById(UUID id, Person person) {
         personDao.updatePersonById(id,person);
    }
}
