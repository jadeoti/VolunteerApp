/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.view;

import io.github.jadeoti.voluntr.entity.Volunteer;
import io.github.jadeoti.voluntr.service.MembershipService;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.picketlink.Identity;
import org.picketlink.idm.model.basic.User;

/**
 *
 * @author Morph-Deji
 */
@Named(value = "homeView")
@RequestScoped
public class HomeView {

    private Logger logger = Logger.getLogger(HomeView.class.getName());

    @Inject
    private Identity identity;

    @Inject
    MembershipService membershipService;

    private Volunteer volunteer;

    /**
     * Creates a new instance of HomeView
     */
    public HomeView() {
    }

    @PostConstruct
    void init() {
        volunteer = new Volunteer();
    }

    public String login() {
        if (identity.isLoggedIn()) {
            return "/index?faces-redirect=true";
        }

        Identity.AuthenticationResult result = identity.login();
        if (Identity.AuthenticationResult.FAILED.equals(result)) {
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Login Failed",
                    "One of username or password is not correct.");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return "";
        }
        return "/faces/volunteer/index.xhtml?faces-redirect=true";
    }

    public String signup(Volunteer volunteer) {
        if (identity.isLoggedIn()) {
            return "/index?faces-redirect=true";
        }

        // check if username exist
//        boolean userExists = membershipService.usernameExists(volunteer.getUsername());
//        if(userExists){
//            FacesMessage facesMessage = new FacesMessage(
//                    FacesMessage.SEVERITY_ERROR, "Account Creation Failed",
//                    "The username supplied has been used.");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            return "";
//        }
//        
//        User user = new User(volunteer.getUsername());
//        user.setEmail(volunteer.getEmail());
//        user.setFirstName(volunteer.getFirstName());
//        user.setLastName(volunteer.getLastName());
//        
//        membershipService.addUser(user, volunteer.getPassword());
        
        return "/faces/volunteer/index.xhtml?faces-redirect=true";
    }

    public String logout() {
        identity.logout();
        return "/index?faces-redirect=true";
    }

}