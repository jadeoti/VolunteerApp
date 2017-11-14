/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.persistence;

import io.github.jadeoti.voluntr.entity.Volunteer;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Morph-Deji
 */
@Stateless
public class MembershipPersistenceService {
    
    @PersistenceUnit(name = "io.gitub.jadeoti.voluntrPU")
    EntityManager em;

    public Volunteer create(Volunteer volunteer) {
        em.persist(volunteer);
        return volunteer;
    }

    public Volunteer disengageVolunteer(int volunteerId, Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Volunteer> findActiveVolunteers() {
        return em.createNamedQuery("Volunteer.findActive").getResultList();
    }

    public List<Volunteer> findInactiveVolunteers() {
        return em.createNamedQuery("Volunteer.findInactive").getResultList();
    }
    
    
}
