/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.view.account;

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
    
    private User user;

    /**
     * Creates a new instance of HomeView
     */
    public HomeView() {
    }

    @PostConstruct
    void init() {
        volunteer = new Volunteer();
        user = new User();
    }
   
}
