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


    @Override
    public Registrant findRegistrantById(Long registrantId){
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Registrant registrant = (Registrant) session.load(Registrant.class, registrantId);

        session.getTransaction().commit();
        return registrant;
    }

    @Override
    public void createRegistrant(Registrant registrant){

        Session session = sessionFactory.getCurrentSession();

        session.save(registrant);
        session.getTransaction().commit();
    }

    @Override
    public Registrant findRegistrantByEmail(String email) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
