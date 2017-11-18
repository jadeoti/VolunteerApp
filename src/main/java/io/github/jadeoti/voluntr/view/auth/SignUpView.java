/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.view.auth;

import io.github.jadeoti.voluntr.service.MembershipService;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.picketlink.common.util.StringUtil;
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
    
    @Inject
    private MembershipService membershipService; 
    
    @Inject
    private FacesContext facesContext;

    /**
     * Creates a new instance of SignUpView
     */
    public SignUpView() {
    }
    
    @PostConstruct
    public void init(){
        user = new User();
    }
    
    /**
     * Sign up
     * User may not necessarily be volunteering; status could be inactive at
     * the moment
     * @param user
     * @param password
     * @return 
     */
    public String save(){
        // validate fields
        // 
        System.out.println(">>>>>>>>>>> Login name " + user.getLoginName());
        boolean userExists = membershipService.usernameExists(user.getLoginName());
        if(userExists){
            FacesContext.getCurrentInstance()
                    .addMessage("reg:username", 
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Input","The username supplied is not available."));
          return null;
        }
        
        // validate firstname
        boolean invalidFirstname = StringUtil.isNullOrEmpty(user.getFirstName());
        if(invalidFirstname){
            FacesContext.getCurrentInstance()
                    .addMessage("reg:username",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Input", "The username supplied is not available."));
            return null;
        }
        
        
        membershipService.addUser(user, password);
        return "index?faces-redirect=true";        
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
    
    
    
    
    
}
