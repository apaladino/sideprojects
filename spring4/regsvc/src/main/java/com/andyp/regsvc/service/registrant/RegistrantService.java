package com.andyp.regsvc.service.registrant;

import com.andyp.regsvc.domain.Registrant;
import com.andyp.regsvc.exceptions.RestConflictException;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Andy Paladino
 * @version: 10/3/14
 */
public interface RegistrantService {

    @Transactional
    Registrant findRegistrantById(Long registrantId);

    @Transactional
    Long createRegistrant(Registrant registrant) throws RestConflictException;

    @Transactional
    Registrant findRegistrantByEmail(String email);
}
