package com.apaladino.controller;

import com.apaladino.data.AddressDataManager;
import com.apaladino.data.PersonDataManager;
import com.apaladino.data.model.Address;
import com.apaladino.data.model.Person;
import com.apaladino.service.PersonService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.Assert.*;

public class PersonControllerTest extends AbstractControllerTest {

    @Autowired
    PersonService personService;

    @Autowired
    AddressDataManager addressDataManager;

    @Before
    public void setUp(){
        super.setUp();
    }

    @Test
    public void listPersonsTest() throws Exception {
        String url = "/person";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(status, HttpStatus.OK.value());

        Person[] personList = mapFromJson(result.getResponse().getContentAsString(), Person[].class);
        assertEquals(personList.length, PersonDataManager.PAGE_SIZE);
    }

    @Test
    public void listPersonsTest_withPageParam() throws Exception {
        String url = "/person?page=2";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(status, HttpStatus.OK.value());

        Person[] personList = mapFromJson(result.getResponse().getContentAsString(), Person[].class);
        assertEquals(personList.length, PersonDataManager.PAGE_SIZE);
    }

    @Test
    public void listPersonTest_withIncludeAddressFlag() throws Exception{
        String url = "/person?includeAddress=true";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(status, HttpStatus.OK.value());

        Person[] personList = mapFromJson(result.getResponse().getContentAsString(), Person[].class);
        assertEquals(personList.length, PersonDataManager.PAGE_SIZE);

        // verify address list exists for person with id:1
        for(Person p : personList){
            if(p.getId().intValue() == 1){
                List<Address> addressList = p.getAddresses();
                assertEquals(1, addressList.size());
                Address address = addressList.get(0);
                assertEquals(1, address.getId().intValue());
                assertEquals("100 someplace lane", address.getStreet());
                assertEquals("Springfield", address.getCity());
                assertEquals("MA", address.getState());
                assertEquals("04111", address.getPostalCode());
            }else{
                assertEquals(0, p.getAddresses().size());
            }
        }
    }

    @Test
    public void createPerson() throws Exception{
        String url = "/person";
        Person person = new Person(200, "sammy", "hagar");

        String inputJson = new Gson().toJson(person);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Person is created successfully");
    }

    @Test
    public void createPerson_alreadyExists() throws Exception{
        String url = "/person";
        Person person = new Person(1, "Andy", "Paladino");

        String inputJson = new Gson().toJson(person);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.CONFLICT.value(), status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Already a person with id: 1");
    }

    @Test
    public void createPerson_missingRequiredFields() throws Exception{
        String url = "/person";
        Person person = new Person(5, null, "Paladino");

        String inputJson = new Gson().toJson(person);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.BAD_REQUEST.value(), status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "missing first name attribute");
    }

    @Test
    public void editPerson() throws Exception{
        String url = "/person";
        Person person = new Person(1, "Andreas", "Paladino");

        String inputJson = new Gson().toJson(person);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Person is updated successfully");

        // double check to see that the person values have been updated
        Person updatedPerson = personService.findPersonById(person.getId());
        assertEquals(person.getId(), updatedPerson.getId());
        assertEquals(person.getFirstName(), updatedPerson.getFirstName());
        assertEquals(person.getLastName(), updatedPerson.getLastName());
    }

    @Test
    public void editPerson_badRequest() throws Exception{
        String url = "/person";
        Person person = new Person(1, "Andreas", null);

        String inputJson = new Gson().toJson(person);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.BAD_REQUEST.value(), status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "missing last name attribute");
    }

    @Test
    public void editPerson_notFound() throws Exception{
        String url = "/person";
        Person person = new Person(101, "Andreas", "jones");

        String inputJson = new Gson().toJson(person);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Unable to find person with id: 101");
    }

    @Test
    public void deletePerson() throws Exception{
        String url = "/person/1";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Person deleted successfully");
    }

    @Test
    public void deletePerson_notFound() throws Exception{
        String url = "/person/5000";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Unable to find person with id: 5000");
    }

    @Test
    public void testCountPersons() throws Exception{
        String url = "/person/count";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("should have returned 12 records", "12", content);
    }

    @Test
    public void testAddAddressToPerson() throws Exception{
        String url = "/person/2/address";
        Address address = new Address(2, "20 main street", "Auburn", "ME", "04223");
        Gson gson = new Gson();
        String inputJson = gson.toJson(address);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url).
            contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(), status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("Address successfully added to person with id: 2", content);
    }

    @Test
    public void testAddAddressToPerson_addressAlreadyExists() throws Exception{
        String url = "/person/1/address";
        Address address = new Address(1, "20 main street", "Auburn", "ME", "04223");
        Gson gson = new Gson();
        String inputJson = gson.toJson(address);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url).
            contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.CONFLICT.value(), status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("address already exists with address id: 1", content);
    }

    @Test
    public void testAddAddressToPerson_personNotFound() throws Exception{
        String url = "/person/5000/address";
        Address address = new Address(1, "20 main street", "Auburn", "ME", "04223");
        Gson gson = new Gson();
        String inputJson = gson.toJson(address);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url).
            contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("Unable to find person with id: 5000", content);
    }

    @Test
    public void testEditAddress() throws Exception{
        String url = "/address/1";
        Address address = new Address(1, "20 main street", "Auburn", "ME", "04223");
        Gson gson = new Gson();
        String inputJson = gson.toJson(address);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(url).
            contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("address updated successfully", content);

        // verify address was updated
        Address updatedAddress = addressDataManager.findAddressById(address.getId());
        assertEquals(address.getId().intValue(), updatedAddress.getId().intValue());
        assertEquals(address.getStreet(), updatedAddress.getStreet());
        assertEquals(address.getCity(), updatedAddress.getCity());
        assertEquals(address.getState(), updatedAddress.getState());
        assertEquals(address.getPostalCode(), updatedAddress.getPostalCode());
    }

    @Test
    public void testEditAddress_notFound() throws Exception {
        String url = "/address/5000";
        Address address = new Address(5000, "20 main street", "Auburn", "ME", "04223");
        Gson gson = new Gson();
        String inputJson = gson.toJson(address);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(url).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("unable to find address with id: 5000", content);
    }

    @Test
    public void testEditAddress_badRequest() throws Exception {
        String url = "/address/5000";
        Address address = new Address(5000, null, "Auburn", "ME", "04223");
        Gson gson = new Gson();
        String inputJson = gson.toJson(address);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(url).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.BAD_REQUEST.value(), status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("missing street attribute", content);
    }

    @Test
    public void testDeleteAddress() throws Exception{
        String url = "/address/1";

        // verify address exists prior to delete
        Address address = addressDataManager.findAddressById(1);
        assertNotNull(address);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        // verify address no longer exists
        Address deletedAddress = addressDataManager.findAddressById(1);
        assertNull(deletedAddress);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("address deleted successfully", content);
    }

    @Test
    public void testDeleteAddress_notFound() throws Exception{
        String url = "/address/5000";

        // verify address exists prior to delete
        Address address = addressDataManager.findAddressById(1);
        assertNotNull(address);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("unable to find address with id: 5000", content);
    }
}