package com.apaladino.data;

import com.apaladino.data.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Data manager for Address domain object
 *
 * @author Andy Paladino
 * @version June 28, 2020
 */

@Repository
public class AddressDataManager {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final static String FIND_ADDRESS_FOR_PERSON_QRY = "select a.* from address a " +
            "inner join PERSON_ADDRESS_MAP pam ON a.id = pam.address_id where pam.person_id=?";
    private final static String FIND_ADDRESS_BY_ID_QRY = "select * from address where id=?";
    private final static String INSERT_ADDRESS_STMT = "insert into address VALUES( ?, ?, ?, ?, ?)";
    private final static String INSERT_PERSON_ADDRESS_MAPPING_STMT = "insert into PERSON_ADDRESS_MAP VALUES( ?, ?)";
    private final static String UPDATE_ADDRESS_STMT = "update address set street=?, city=?, state=?, postal_code=? " +
            "where id=?";
    private final static String DELETE_ADDRESS_STMT = "delete from address where id=?";
    private final static String DELETE_PERSON_ADDRESS_MAPPING_STMT = "delete from PERSON_ADDRESS_MAP where address_id=?";

    /**
     * looks up list of addresses for personId
     *
     * @param personId
     * @return list of addresses
     */
    public List<Address> findAddressesForPersonId(Integer personId) {
       return jdbcTemplate.query(FIND_ADDRESS_FOR_PERSON_QRY, new Object[]{personId},
                new AddressRowMapper());
    }

    /**
     * look up an address by address id
     * @param id - address id
     * @return
     */
    public Address findAddressById(Integer id) {
        List<Address> results = jdbcTemplate.query(FIND_ADDRESS_BY_ID_QRY, new Object[]{id}, new AddressRowMapper());

        return (results.isEmpty()) ? null : results.get(0);
    }

    /**
     * persist a new address
     *
     * @param address
     */
    public void addAddress(Address address) {
        int rowsUpdated = jdbcTemplate.update(INSERT_ADDRESS_STMT, address.getId(), address.getStreet(),
                address.getCity(), address.getState(), address.getPostalCode());
        if(rowsUpdated ==0){
            throw new IllegalStateException("should have inserted one record!");
        }
    }

    /**
     * add a person to address mapping entry
     *
     * @param personId
     * @param addressId
     */
    public void addAddressPersonMapping(Integer personId, Integer addressId) {
        int rowsUpdated = jdbcTemplate.update(INSERT_PERSON_ADDRESS_MAPPING_STMT, personId, addressId);
        if(rowsUpdated == 0){
            throw new IllegalStateException("should have inserted one record!");
        }
    }

    /**
     * Update an existing address
     *
     * @param address
     */
    public void updateAddress(Address address) {
        int rowsUpdated = jdbcTemplate.update(UPDATE_ADDRESS_STMT, address.getStreet(),
                address.getCity(), address.getState(), address.getPostalCode(), address.getId());

        if(rowsUpdated == 0){
            throw new IllegalStateException("should have updated one record!");
        }
    }

    /**
     * delete an address record
     *
     * @param id
     */
    public void deleteAddress(Integer id) {
        int rowsUpdated = jdbcTemplate.update(DELETE_ADDRESS_STMT, id);
        if(rowsUpdated == 0){
            throw new IllegalStateException("should have deleted one record!");
        }
    }

    /**
     * delete a person to address mapping
     *
     * @param addressId
     */
    public void deletePersonAddressMapping(Integer addressId) {
        int rowsUpdated = jdbcTemplate.update(DELETE_PERSON_ADDRESS_MAPPING_STMT, addressId);
        if(rowsUpdated == 0){
            throw new IllegalStateException("should have deleted one record!");
        }
    }

    // internal class to map result set data to an Address object
    class AddressRowMapper implements RowMapper<Address> {
        @Override
        public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Address(rs.getInt("id"),
                    rs.getString("street"),
                    rs.getString("city"),
                    rs.getString("state"),
                    rs.getString("postal_code"));
        }
    }
}