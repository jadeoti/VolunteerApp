/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.persistence;

import io.github.jadeoti.voluntr.entity.Applicant;
import io.github.jadeoti.voluntr.entity.ApplicationDecision;
import io.github.jadeoti.voluntr.entity.Volunteer;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.picketlink.annotations.PicketLink;

/**
 *
 * @author Morph-Deji
 */
@Stateless
public class MembershipPersistenceService implements Serializable{

    @Inject
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Volunteer create(Volunteer volunteer) {
        em.persist(volunteer);
        return volunteer;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public <T> T insert(T t){
        em.persist(t);
        return t;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public <T> void update(T t) {
        em.merge(t);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public <T> T findById(Class<T> t, Long id){
        return em.find(t, id);
    }

    public <T> void delete(T t) {
        em.remove(em.merge(t));
    }
    public Volunteer disengageVolunteer(int volunteerId, Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Volunteer> findAll() {
        return em.createNamedQuery("Volunteer.findAll").getResultList();
    }

    public List<Volunteer> findActiveVolunteers() {
        return em.createNamedQuery("Volunteer.findActive").getResultList();
    }

    public List<Volunteer> findInactiveVolunteers() {
        return em.createNamedQuery("Volunteer.findInactive").getResultList();
    }

    public List<Volunteer> seachVolunteer(String description) {

        throw new UnsupportedOperationException("Not supported yet.");
    }
    public List<Applicant> getApplicants() {
        return em.createNamedQuery("Applicant.findAll").getResultList();
    }
    
    public List<Applicant> getApplicantsByDecision(ApplicationDecision applicationDecision) {
        return em.createNamedQuery("Applicant.findApplicantByDecision")
                .setParameter("decision", applicationDecision)
                .getResultList();
    }

}
