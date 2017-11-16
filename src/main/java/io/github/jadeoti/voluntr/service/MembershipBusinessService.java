/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.service;

import io.github.jadeoti.voluntr.entity.Volunteer;
import io.github.jadeoti.voluntr.persistence.MembershipPersistenceService;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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
    
    public List<Volunteer> findVolunteers(String description){
        List<Volunteer> volunteers = null;
        if(description == null){
            volunteers = persistenceService.findAll();
        }else{
            volunteers = persistenceService.seachVolunteer(description);
        }        
        return volunteers;
        
    }
    
    
    
}
