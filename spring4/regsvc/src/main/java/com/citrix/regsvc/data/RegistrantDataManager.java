package com.citrix.regsvc.data;

import com.citrix.regsvc.domain.Registrant;

/**
 * Created by apaladino on 10/2/14.
 */
public interface RegistrantDataManager {

    Registrant findRegistrantById(Long registrantId);

    void createRegistrant(Registrant registrant);

    Registrant findRegistrantByEmail(String email);
}
