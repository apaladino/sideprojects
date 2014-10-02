package com.citrix.regsvc.data;

import com.citrix.regsvc.domain.Registrant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by apaladino on 10/2/14.
 */
@Repository
public class RegistrantDataManagerImpl implements RegistrantDataManager {

    @Autowired
    private SessionFactory sessionFactory;


    public void AddRegistrant(Registrant registrant){

        Session session = sessionFactory.getCurrentSession();

        session.save(registrant);
        session.getTransaction().commit();
    }
}
