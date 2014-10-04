package com.citrix.regsvc.controller;

import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import com.citrix.regsvc.domain.Registrant;
import com.citrix.regsvc.exceptions.RestConflictException;
import com.citrix.regsvc.service.RegistrantService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void createRegistrant(@ModelAttribute Registrant registrant, BindingResult result,
            HttpServletResponse response) throws RestConflictException {


        Assert.notNull(registrant);
        Assert.notNull(registrant.getFirstName());
        Assert.notNull(registrant.getLastName());
        Assert.notNull(registrant.getEmail());

        registrant.setCreateTime(new Date());

        registrantService.createRegistrant(registrant);

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
