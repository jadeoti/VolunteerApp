/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.view;

import io.github.jadeoti.voluntr.entity.Volunteer;
import io.github.jadeoti.voluntr.persistence.MembershipPersistenceService;
import io.github.jadeoti.voluntr.service.MembershipService;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author Morph-Deji
 */
@Named(value = "registration")
@ViewScoped
public class RegistrationService implements Serializable {

    Logger logger = Logger.getLogger(RegistrationService.class.getName());
    
    //@Inject
    //private Logger logger;
    
    
    private Volunteer volunteer = new Volunteer();

    private List<Volunteer> volunteers;

    @Inject
    private MembershipService membershipService;

    @EJB
    private MembershipPersistenceService persistenceService;

    @PostConstruct
    void init() {
        volunteers = persistenceService.findActiveVolunteers();
    }

    public RegistrationService() {
    }

    public String submitVoluteer() {
        if (null != volunteer) {
            System.out.println("Persisting Voluteer ::: " + volunteer);
            persistenceService.create(volunteer);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucessful", volunteer.getFirstName() + " voluteer created"));
            //volunteer = new Volunteer();
            //init();
            return "/faces/volunteer/index.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "voluteer cannot be empty"));
            return null;
        }

    }

    public void editVolunteer(Volunteer volunteer) {
        if (null != volunteer) {
            this.volunteer = persistenceService.findById(Volunteer.class, volunteer.getId());
        }
    }

    public void deleteVolunteer(Volunteer volunteer) {
        if (null != volunteer) {
            this.volunteer = persistenceService.findById(Volunteer.class, volunteer.getId());
            persistenceService.delete(this.volunteer);
            //init();
            //this.volunteer = new Volunteer();
        }
    }

    public void updateVoluteer() {
        if (null != this.volunteer) {

            persistenceService.update(this.volunteer);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, volunteer.getFirstName() + " voluteer updated", volunteer.getFirstName() + " voluteer created"));
            //this.volunteer = new Volunteer();
            //init();
        }
    }

    public List<Volunteer> getVolunteers(String query) {
        volunteers = membershipService.findVolunteers(query);
        return volunteers;

    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public List<Volunteer> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }

}
