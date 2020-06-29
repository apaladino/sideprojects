package com.apaladino.data;

import com.apaladino.data.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Data manager for person domain object
 *
 * @author Andy Paladino
 * @version June 28, 2020
 */
@Repository
public class PersonDataManager {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public final static int PAGE_SIZE = 5;

    private static final String GET_PERSON_BY_ID_QRY = "select id, first_name, last_name from PERSON where id=?";
    private static final String LIST_PERSONS_QRY = "select id, first_name, last_name from PERSON order by id limit ? offset ?";
    private static final String INSERT_PERSON_STMT = "INSERT INTO PERSON VALUES (?, ?, ?)";
    private static final String UPDATE_PERSON_STMT = "UPDATE PERSON set first_name=?, last_name=? where id=?";
    private static final String DELETE_PERSON_STMT = "DELETE FROM PERSON where id=?";
    private static final String PERSON_COUNT_QRY = "select count(id) from PERSON";

    /**
     * look up a person by person id
     *
     * @param id
     * @return
     */
    public Person findById(Integer id){
        List<Person> results = jdbcTemplate.query(GET_PERSON_BY_ID_QRY, new Object[]{id}, new PersonRowMapper());

        return results.isEmpty() ? null : results.get(0);
    }

    /**
     * look up a list of persons. Note: if no page parameter is provided, it will return only the 1st page of data
     *
     * @param page - optional page parameter, will default to 1.
     * @return
     */
    public List<Person> listPersons(Integer page) {
        int offSet = page.intValue() == 1 ? 0 : (page -1) * PAGE_SIZE;

        return jdbcTemplate.query(LIST_PERSONS_QRY, new Object[] { PAGE_SIZE, offSet},
                new PersonRowMapper());
    }

    /**
     * add a new person
     *
     * @param person
     */
    public void addPerson(Person person) {
       int rowsUpdated =  jdbcTemplate.update(INSERT_PERSON_STMT, person.getId(), person.getFirstName(), person.getLastName());
       if(rowsUpdated == 0)
           throw new IllegalStateException("should have inserted one record!");
    }

    /**
     * update a person with same person id
     *
     * @param person
     */
    public void updatePerson(Person person) {
        int rowsUpdated = jdbcTemplate.update(UPDATE_PERSON_STMT, person.getFirstName(), person.getLastName(), person.getId());
        if(rowsUpdated == 0)
            throw new IllegalStateException("should have updated one record!");
    }

    /**
     * delete a person with same person id
     *
     * @param id
     */
    public void deletePerson(Integer id) {
        int rowsUpdated = jdbcTemplate.update(DELETE_PERSON_STMT, id);
        if(rowsUpdated == 0)
            throw new IllegalStateException("should have deleted one record!");
    }

    /**
     * look up a count of all persons
     *
     * @return count of all persons
     */
    public Integer getPersonCount() {
        return jdbcTemplate.queryForObject(PERSON_COUNT_QRY, Integer.class);
    }

    // Utility class to allow us to map result set to a person domain object
    class PersonRowMapper implements RowMapper<Person>{
            @Override
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Person(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
            }
    }
}
