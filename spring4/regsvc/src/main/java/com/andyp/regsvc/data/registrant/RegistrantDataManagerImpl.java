package com.andyp.regsvc.data.registrant;

import com.andyp.regsvc.domain.Registrant;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * Created by apaladino on 10/2/14.
 */
@Repository
public class RegistrantDataManagerImpl implements RegistrantDataManager {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Registrant findRegistrantById(Long registrantId){

        Assert.notNull(registrantId);
        Registrant registrant = (Registrant) sessionFactory.getCurrentSession()
                .get(Registrant.class, registrantId);

        if(registrant.getLinkedInProfile() != null){
            Hibernate.initialize(registrant.getLinkedInProfile().getPositions());
        }

        return registrant;
    }

    @Override
    public Long createRegistrant(Registrant registrant){

        Session session = sessionFactory.getCurrentSession();

        Long registrantKey = (Long) session.save(registrant);

        if(registrant.getLinkedInProfile() != null){
            session.save(registrant.getLinkedInProfile());
        }
        return registrantKey;
    }

    @Override
    public Registrant findRegistrantByEmail(String email) {

        Assert.isTrue(email != null && !email.isEmpty());

        Session session = sessionFactory.getCurrentSession();

        String qryStr = "from Registrant where email = :email";

        Query query = session.createQuery(qryStr);
        query.setString("email", email);
        Registrant registrant = (Registrant) query.uniqueResult();
        return registrant;
    }

}
