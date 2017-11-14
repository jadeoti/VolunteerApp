/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.service;

import io.github.jadeoti.voluntr.entity.Volunteer;
import io.github.jadeoti.voluntr.persistence.MembershipPersistenceService;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Morph-Deji
 */
@Stateless
public class MembershipBusinessService {

    @EJB
    MembershipPersistenceService persistenceService;

    public Volunteer register(Volunteer volunteer) {
        Volunteer person = persistenceService.create(volunteer);
        return person;        
    }
    
    
    
}
