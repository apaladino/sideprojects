package com.citrix.regsvc.data.registrant;

import com.citrix.regsvc.domain.Registrant;

/**
 * Created by apaladino on 10/2/14.
 */
public interface RegistrantDataManager {

    Registrant findRegistrantById(Long registrantId);

    Long createRegistrant(Registrant registrant);

    Registrant findRegistrantByEmail(String email);
}