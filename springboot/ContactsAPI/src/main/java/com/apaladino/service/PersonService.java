package com.apaladino.service;

import com.apaladino.data.AddressDataManager;
import com.apaladino.data.PersonDataManager;
import com.apaladino.data.model.Address;
import com.apaladino.data.model.Person;
import com.apaladino.error.AlreadyExistsException;
import com.apaladino.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Service level object for the Person domain object. Adds additional logic to basic CRUD operations
 *
 * @author Andy Paladino
 * @version June 28, 2020
 */
@Service
public class PersonService {

    @Autowired
    PersonDataManager personDataManager;

    @Autowired
    AddressDataManager addressDataManager;

    /**
     * look up person by id. Will throw a NotFoundException if person is not found
     *
     * @param id
     * @return
     * @throws NotFoundException
     */
    public Person findPersonById(Integer id) throws NotFoundException {
        Assert.notNull(id, "Missing required parameter");

        Person p = personDataManager.findById(id);
        if(p == null){
            throw new NotFoundException("Unable to find person with id: " + id);
        }
        return p;
    }

    /**
     * look up a list of persons, page by page.
     *
     * @param page, page to retrieve. default to 1
     * @param includeAddress - flag to include any addresses associated with person
     *
     * @return
     */
    public List<Person> listPersons(Integer page, Boolean includeAddress) {
        List<Person> people = personDataManager.listPersons(page);

        if(includeAddress){
            for(Person person : people){
                List<Address> addresses = addressDataManager.findAddressesForPersonId(person.getId());
                person.setAddresses(addresses);
            }
        }
        return people;
    }

    /**
     * create a new person
     *
     * @param person
     * @throws AlreadyExistsException
     */
    public void createPerson(Person person) throws AlreadyExistsException {
        Person p = personDataManager.findById(person.getId());
        if(p != null){
            throw new AlreadyExistsException("Already a person with id: " + person.getId());
        }

        personDataManager.addPerson(person);
    }

    /**
     * updates a person
     *
     * @param person
     * @throws NotFoundException
     */
    public void updatePerson(Person person) throws NotFoundException {
        Person p = findPersonById(person.getId()); // will throw NotFoundException if not found
        personDataManager.updatePerson(person);
    }

    /**
     * delete a person
     *
     * @param id
     * @throws NotFoundException
     */
    public void deletePerson(Integer id) throws NotFoundException {
        Person p = findPersonById(id); // with throw a NotFoundException if person does not exist

        personDataManager.deletePerson(id);
    }

    /**
     * look up person count
     *
     * @return
     */
    public Integer getPersonCount() {
        return personDataManager.getPersonCount();
    }

    /**
     * add an address to a person
     *
     * @param personId
     * @param address
     * @throws NotFoundException
     * @throws AlreadyExistsException
     */
    public void addAddressToPerson(Integer personId, Address address) throws NotFoundException, AlreadyExistsException {
        Person p = findPersonById(personId);
        Address a = addressDataManager.findAddressById(address.getId());
        if(a != null){ // address already exists
            throw new AlreadyExistsException("address already exists with address id: " + address.getId());
        }

        addressDataManager.addAddress(address);
        addressDataManager.addAddressPersonMapping(personId, address.getId());
    }

    /**
     * update an address with same address id
     *
     * @param address
     * @throws NotFoundException
     */
    public void updateAddress(Address address) throws NotFoundException {
        Address a = addressDataManager.findAddressById(address.getId());
        if(a == null) { // not found
            throw new NotFoundException("unable to find address with id: " + address.getId());
        }

        addressDataManager.updateAddress(address);
    }


    /**
     * delete an address with same id
     *
     * @param id
     * @throws NotFoundException
     */
    public void deleteAddress(Integer id) throws NotFoundException {
        Address a = addressDataManager.findAddressById(id);
        if(a == null) { // not found
            throw new NotFoundException("unable to find address with id: " + id);
        }

        addressDataManager.deleteAddress(id);
        addressDataManager.deletePersonAddressMapping(id);
    }
}
