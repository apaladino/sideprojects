package com.citrix.regsvc.service;

import com.citrix.regsvc.data.RegistrantDataManager;
import com.citrix.regsvc.domain.Registrant;
import com.citrix.regsvc.exceptions.RestConflictException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Andy Paladino
 * @version: 10/3/14
 */
@Service
public class RegistrantServiceImpl implements RegistrantService {

    private final static Logger logger = Logger.getLogger(RegistrantService.class);

    @Autowired
    private RegistrantDataManager registrantDataManager;

    @Override
    public Registrant findRegistrantById(Long registrantId){
        return registrantDataManager.findRegistrantById(registrantId);
    }

    @Override
    public Long createRegistrant(Registrant registrant) throws RestConflictException {

        Registrant r = registrantDataManager.findRegistrantByEmail(registrant.getEmail());
        if(r != null){
            logger.info("Can not create registrant. Registrant already exists.");
            throw new RestConflictException("Registrant already exists with email: " + registrant.getEmail());
        }

        return registrantDataManager.createRegistrant(registrant);
    }

    public void setRegistrantDataManager(RegistrantDataManager registrantDataManager) {
        this.registrantDataManager = registrantDataManager;
    }
}
