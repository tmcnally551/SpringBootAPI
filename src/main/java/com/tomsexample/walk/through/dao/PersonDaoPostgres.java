package com.tomsexample.walk.through.dao;

import com.tomsexample.walk.through.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDaoPostgres implements PersonDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDaoPostgres(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public List<Person> getAllPeople() {
        final String sql = "select id,name from person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id,name);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "select id,name from person where id = ?";
        Person person = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(personId,name);
        });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql = "delete from person where id = ?";
        return 0;

    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return 0;
    }
}
