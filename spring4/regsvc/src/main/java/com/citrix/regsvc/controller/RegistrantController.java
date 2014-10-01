package com.citrix.regsvc.controller;

import com.citrix.regsvc.domian.Registrant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by apaladino on 9/28/14.
 */
@RestController
public class RegistrantController {

    @Autowired
    private DataSource dataSource;


    @RequestMapping(value="/registrant/{registrantId}", method = GET)
    public Registrant getRegistrantByID(@PathVariable("registrantId") @NotNull Long registrantId){

        System.out.println("Registrant request recieved: " + registrantId);

        Assert.notNull(registrantId);

        if(registrantId == 111L){
            Registrant r = new Registrant();
            r.setEmail("test@jedix.com");
            r.setFirstName("first");
            r.setLastName("last");
            return r;
        }
        return null;
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequest(Exception e, HttpServletRequest request){


        return e.getMessage();
    }
}
