package com.citrix.regsvc.service;

import com.citrix.regsvc.domain.Registrant;
import com.citrix.regsvc.exceptions.RestConflictException;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Andy Paladino
 * @version: 10/3/14
 */
public interface RegistrantService {

    Registrant findRegistrantById(Long registrantId);

    @Transactional
    Long createRegistrant(Registrant registrant) throws RestConflictException;
}
