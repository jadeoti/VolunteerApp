/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.view.account;

import io.github.jadeoti.voluntr.entity.Volunteer;
import io.github.jadeoti.voluntr.service.MembershipService;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.picketlink.Identity;
import org.picketlink.idm.model.basic.User;

/**
 *
 * @author Morph-Deji
 */
@Named(value = "signInView")
@RequestScoped
public class SignInView {

    private Logger logger = Logger.getLogger(SignInView.class.getName());

    @Inject
    private Identity identity;

    @Inject
    MembershipService membershipService;

    private User volunteer;

    /**
     * Creates a new instance of HomeView
     */
    public SignInView() {
    }

    @PostConstruct
    void init() {
        volunteer = new User();
    }

    public String login() {
        if (identity.isLoggedIn()) {
            return "/faces/volunteer/applicants.xhtml?faces-redirect=true";
        }

        Identity.AuthenticationResult result = identity.login();
        if (Identity.AuthenticationResult.FAILED.equals(result)) {
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Login Failed",
                    "One of username or password is not correct.");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return "";
        }
        return "/faces/volunteer/applicants.xhtml?faces-redirect=true";
    }

    public String signup(Volunteer volunteer) {
        if (identity.isLoggedIn()) {
            return "/index?faces-redirect=true";
        }
        return "/faces/volunteer/index.xhtml?faces-redirect=true";
    }

    public String logout() {
        identity.logout();
        return "/faces/index.xhtml?faces-redirect=true";
    }

}
