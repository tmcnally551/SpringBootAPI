package com.tomsexample.walk.through.dao;

import com.tomsexample.walk.through.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("personDao")
public class PersonDaoDataAccess implements PersonDao {

    private final static List<Person> dbPerson = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        dbPerson.add(new Person(id,person.getName()));
        return 1;
    }
    @Override
    public List<Person> getAllPeople(){
        return dbPerson;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return dbPerson.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> person = selectPersonById(id);
        if(person.isPresent()){
            dbPerson.remove(person.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person updatePerson) {
        return selectPersonById(id)
                .map(person -> {
                    int indexOfPersonToUpdate = dbPerson.indexOf(person);
                    if(indexOfPersonToUpdate >= 0){
                        dbPerson.set(indexOfPersonToUpdate,new Person(id,updatePerson.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
