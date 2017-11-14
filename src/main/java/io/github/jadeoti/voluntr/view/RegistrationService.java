/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.view;

import io.github.jadeoti.voluntr.entity.Volunteer;
import io.github.jadeoti.voluntr.service.MembershipBusinessService;
import io.github.jadeoti.voluntr.viewmodel.Person;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author Morph-Deji
 */
@Named(value = "registration")
@RequestScoped
public class RegistrationService {
    
    Logger logger = Logger.getLogger(RegistrationService.class.getName());
    
    @Inject
    private MembershipBusinessService membershipService;
            
    public RegistrationService() {
    }
    
    public void submit(Person person){
        if(person == null){
            // TODO: Notify error
            logger.info("Saving volunteer but backing bean is null");

        }else {
            logger.info(String.format("Saving volunteer info. Firstname:%s, Lastname:%s, Email:%s",
                    person.getFirstName(), person.getLastName(), person.getEmail()));
            Volunteer volunteer = new Volunteer();
            volunteer.setFirstName(person.getFirstName());
            volunteer.setMiddleName(person.getMiddleName());
            volunteer.setFirstName(person.getFirstName());
            
            volunteer.setEmail(person.getEmail());
            volunteer.setWantNewsletter(person.isWantNewsletter());
            
            membershipService.register(volunteer);
        }
   
        // TODO: convert person to volunteer
        //membershipService.register(volunteer);
    }
    
}
