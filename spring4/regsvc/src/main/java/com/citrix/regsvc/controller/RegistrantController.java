package com.citrix.regsvc.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import com.citrix.regsvc.domain.Registrant;
import com.citrix.regsvc.exceptions.RestConflictException;
import com.citrix.regsvc.service.registrant.RegistrantService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by apaladino on 9/28/14.
 */
@RestController
@Component
public class RegistrantController {

    @Autowired
    private RegistrantService registrantService;

    private final Logger logger = Logger.getLogger(RegistrantController.class);

    @RequestMapping(value = "/registrant/{registrantId}", method = GET)
    public Registrant getRegistrantByID(@PathVariable("registrantId") @NotNull Long registrantId,
            HttpServletResponse response){

        logger.info("Registrant request recieved: " + registrantId);

        Assert.notNull(registrantId);

        Registrant registrant = registrantService.findRegistrantById(registrantId);

        if(registrant == null){
            // not found
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }else{
            response.setStatus(HttpServletResponse.SC_OK);
        }

        return registrant;
    }

    @RequestMapping(value = "/registrant", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String,String> createRegistrant(@RequestBody Registrant registrant, BindingResult result,
            HttpServletRequest request,
            HttpServletResponse response) throws RestConflictException {


        Assert.notNull(registrant);
        Assert.notNull(registrant.getFirstName(), "Missing firstName parameter.");
        Assert.notNull(registrant.getLastName(), "Missing lastName parameter");
        Assert.notNull(registrant.getEmail(), "Missing email parameter");

        registrant.setCreateTime(new Date());

        Long registrantKey = registrantService.createRegistrant(registrant);
        Assert.notNull(registrantKey,"Unable to create registrant");

        Map<String,String> responseMap = new HashMap<String,String>();
        responseMap.put("registrantKey" , registrantKey.toString());

        return responseMap;
    }


    @ExceptionHandler(Exception.class)
    public String handleError(Exception e, HttpServletResponse response){
        logger.error("Exception thrown: " + e.getMessage(), e);

        if(e instanceof IllegalArgumentException){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }else if(e instanceof RestConflictException){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }else{
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        }

        return e.getMessage();
    }

    public void setRegistrantService(RegistrantService registrantService) {
        this.registrantService = registrantService;
    }
}
