package com.apaladino.controller;

import com.apaladino.data.model.Address;
import com.apaladino.data.model.Person;
import com.apaladino.error.AlreadyExistsException;
import com.apaladino.error.NotFoundException;
import com.apaladino.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for Person domain object
 *
 * @author Andy Paladino
 * @version June 28, 2020
 */
@RestController
public class PersonController {

    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public List<Person> listPersons(@RequestParam(required = false, defaultValue = "1") Integer page,
                                    @RequestParam(required = false, defaultValue = "false") Boolean includeAddress) throws Exception{
        logger.debug("listPersons endpoint called");
        return personService.listPersons(page, includeAddress);
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public Person getPersonById(@PathVariable Integer id) throws Exception{
        logger.debug("getPerson endpoint called");

        return personService.findPersonById(id);
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String createPerson(@RequestBody Person person) throws Exception {
        logger.debug("createPerson api endpoint called");
        validatePersonRequestParam(person); // Note: this would typically belong in an interceptor

        personService.createPerson(person);

        return "Person is created successfully";
    }

    @RequestMapping(value = "/person", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updatePerson(@RequestBody Person person) throws NotFoundException {
        logger.debug("updatePerson api endpoint called");
        validatePersonRequestParam(person); // Note: this would typically belong in an interceptor

        personService.updatePerson(person);

        return "Person is updated successfully";
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    public String deletePerson(@PathVariable Integer id) throws NotFoundException {
        logger.debug("deletePerson endpoint called");
        personService.deletePerson(id);

        return "Person deleted successfully";
    }

    @RequestMapping(value = "/person/count", method = RequestMethod.GET)
    public Integer getPersonCount(){
        logger.debug("getPersonCount endpoint called");
        return personService.getPersonCount();
    }

    @RequestMapping(value = "/person/{id}/address", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String addAddressToPerson(@PathVariable Integer id, @RequestBody Address address) throws NotFoundException, AlreadyExistsException {
        logger.debug("addAddressToPerson endpoint called");
        validateAddressRequestParam(address);

        personService.addAddressToPerson(id, address);
        return "Address successfully added to person with id: " + id;
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.PUT)
    public String updateAddress(@PathVariable Integer id, @RequestBody Address address) throws NotFoundException{
        logger.debug("updateAddress endpoint called");
        validateAddressRequestParam(address);

        personService.updateAddress(address);

        return "address updated successfully";
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
    public String deleteAddress(@PathVariable Integer id) throws NotFoundException {
        logger.debug("deleteAddress endpoint called");
        personService.deleteAddress(id);

        return "address deleted successfully";
    }

    /*
        Helper methods
     */
    private void validatePersonRequestParam(Person person) {
        Assert.notNull(person, "missing request body");
        Assert.isTrue(person.getFirstName() != null && person.getFirstName().length() > 0,
                "missing first name attribute");
        Assert.isTrue(person.getLastName() != null && person.getLastName().length() > 0,
                "missing last name attribute");
    }

    private void validateAddressRequestParam(Address address) {
        Assert.notNull(address, "missing request pody");
        Assert.notNull(address.getId(), "missing id attribute");
        Assert.isTrue(address.getStreet() != null && address.getStreet().length() > 0,
                "missing street attribute");
        Assert.isTrue(address.getCity() != null && address.getCity().length() > 0,
                "missing city attribute");
        Assert.isTrue(address.getState() != null && address.getState().length() > 0,
                "missing state attribute");
        Assert.isTrue(address.getPostalCode() != null && address.getPostalCode().length() > 0,
                "missing postalCode attribute");
    }

    /*
        Exeption handler / response status mappings
     */
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(Exception e){
        return e.getMessage();
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequest(Exception e){ return e.getMessage(); }

    @ExceptionHandler(value = AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleConflict(Exception e){
        return e.getMessage();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleServerError(Exception e){ return e.getMessage();}
}
