/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.view.account;

import io.github.jadeoti.voluntr.service.MembershipService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.picketlink.idm.model.basic.User;

/**
 *
 * @author Morph-Deji
 */
@Named(value = "signUpView")
@RequestScoped
public class SignUpView {

    private User user;

    private String password;

    private boolean wantNewsLetter;
    
    private String essay;

    @Inject
    private MembershipService membershipService;

    @Inject
    private FacesContext facesContext;
    
    @Inject
    private Logger logger;

    /**
     * Creates a new instance of SignUpView
     */
    public SignUpView() {
    }

    @PostConstruct
    public void init() {
        user = new User();
    }

    /**
     * Sign up User may not necessarily be volunteering; status could be
     * inactive at the moment
     *
     * @param user
     * @param password
     * @return
     */
    public String save() {
        // validate fields
        // 
        logger.log(Level.INFO, "Login name:{0}", user.getLoginName());
        
        boolean validUsername = membershipService.validName(user.getLoginName());
        if (!validUsername) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Input", "Username cannot be empty");
            facesContext.addMessage("reg:username", facesMessage);

            UIViewRoot uiViewRoot = facesContext.getViewRoot();
            UIInput usernameUiInput = (UIInput) uiViewRoot
                    .findComponent("reg:username");
            usernameUiInput.setValid(false);

            return null;
        }
        
        boolean userExists = membershipService.usernameExists(user.getLoginName());
        if (userExists) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Input", "The username supplied is not available.");
            facesContext.addMessage("reg:username", facesMessage);
            
            UIViewRoot uiViewRoot = facesContext.getViewRoot();
            UIInput usernameUiInput = (UIInput) uiViewRoot
                    .findComponent("reg:username");
            usernameUiInput.setValid(false);
            
            return null;
        }

        // validate firstname
        boolean validFirstname = membershipService.validName(user.getFirstName());
        if (!validFirstname) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Invalid Input", "Firstname cannot be empty");
            facesContext.addMessage("reg:firstname", facesMessage);
            
            UIViewRoot uiViewRoot = facesContext.getViewRoot();
            UIInput firstnameUiInput = (UIInput) uiViewRoot
                    .findComponent("reg:firstname");
            firstnameUiInput.setValid(false);
            
            return null;
        }
        
        // validate lastname
        boolean validLastname = membershipService.validName(user.getLastName());
        if (!validLastname) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Input", "Lastname cannot be empty");
            facesContext.addMessage("reg:lastname", facesMessage);
            
            UIViewRoot uiViewRoot = facesContext.getViewRoot();
            UIInput lastnameUiInput = (UIInput) uiViewRoot
                    .findComponent("reg:lastname");
            lastnameUiInput.setValid(false);
            
            return null;
        }
        
        
        // validate email
        boolean validEmail = membershipService.validEmail(user.getEmail());
        if (!validEmail) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Input", "Email is not valid");
            facesContext.addMessage("reg:email", facesMessage);
            
            UIViewRoot uiViewRoot = facesContext.getViewRoot();
            UIInput emailUiInput = (UIInput) uiViewRoot
                    .findComponent("reg:email");
            emailUiInput.setValid(false);
            
            return null;
        }
        
        // validate essay
        boolean validEssay = membershipService.validEssay(essay);
        if (!validEssay) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Input", "Essay field cannot be empty.");
            facesContext.addMessage("reg:essay", facesMessage);

            UIViewRoot uiViewRoot = facesContext.getViewRoot();
            UIInput emailUiInput = (UIInput) uiViewRoot
                    .findComponent("reg:essay");
            emailUiInput.setValid(false);

            return null;
        }
        
        // validate password
        boolean validPassword = membershipService.validPassword(password);
        if (!validPassword) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Input", "Password is not valid");
            facesContext.addMessage("reg:password", facesMessage);
            
            UIViewRoot uiViewRoot = facesContext.getViewRoot();
            UIInput passwordUiInput = (UIInput) uiViewRoot
                    .findComponent("reg:password");
            passwordUiInput.setValid(false);
            
            return null;
        }

        membershipService.addUser(user, password, essay);
        user = new User(); // refresh ui
        return "/index.xhtml?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isWantNewsLetter() {
        return wantNewsLetter;
    }

    public void setWantNewsLetter(boolean wantNewsLetter) {
        this.wantNewsLetter = wantNewsLetter;
    }

    public String getEssay() {
        return essay;
    }

    public void setEssay(String essay) {
        this.essay = essay;
    }
    
    

}
