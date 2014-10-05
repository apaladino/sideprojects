package com.citrix.regsvc.data;

import com.citrix.regsvc.domain.Registrant;

import org.hibernate.Query;
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

        String qryStr = "from Registrant where registrantId = :id";

        Query query = session.createQuery(qryStr);
        query.setLong("id", registrantId);
        Registrant registrant = (Registrant) query.uniqueResult();

        return registrant;
    }

    @Override
    public Long createRegistrant(Registrant registrant){

        Session session = sessionFactory.getCurrentSession();
        Long registrantKey = (Long) session.save(registrant);

        if(registrant.getLinkedInProfile() != null){
            session.save(registrant.getLinkedInProfile());
            session.update(registrant);
        }

        return registrantKey;
    }

    @Override
    public Registrant findRegistrantByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();

        String qryStr = "from Registrant where email = :email";

        Query query = session.createQuery(qryStr);
        query.setString("email", email);
        Registrant registrant = (Registrant) query.uniqueResult();

        return registrant;
    }

}
