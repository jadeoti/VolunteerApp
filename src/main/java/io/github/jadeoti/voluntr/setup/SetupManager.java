/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.setup;

import io.github.jadeoti.voluntr.entity.Applicant;
import io.github.jadeoti.voluntr.service.MembershipService;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.User;

/**
 *
 * @author Morph-Deji
 */
@Singleton
@Startup
public class SetupManager {

    @Inject
    PartitionManager partitionManager;
    
    @Inject
    MembershipService membershipService;
    
    @PostConstruct
    public void init(){
//        addUser();
//        addApplicant();
    }
    
    public void addUser(){        
        System.out.println("::::::::: Init running");
        IdentityManager idManager = partitionManager.createIdentityManager();
        User user = BasicModel.getUser(idManager, "admin");
        if (user == null) {
            user = new User("deployer");
            user.setEmail("adedejiadeoti@gmail.com");
            user.setFirstName("Adedeji");
            user.setLastName("Adeoti");

            idManager.add(user);
            idManager.updateCredential(user, new Password("deployer"));
            System.out.println("::::::::: Saved new user");

        }       
        
    }
    
    public void addApplicant(){
        User user = new User("sprtnjoe");
        user.setEmail("sprtnjoe@gmail.com");
        user.setFirstName("Adedeji");
        user.setLastName("Adeoti");
        membershipService.addUser(user, "deji", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        
        
        user = new User("Choe");
        user.setEmail("chloe@gmail.com");
        user.setFirstName("Chloe");
        user.setLastName("Adeoti");
        membershipService.addUser(user, "deji", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        
         user = new User("deji");
        user.setEmail("dejij@gmail.com");
        user.setFirstName("deji");
        user.setLastName("Adeoti");
        membershipService.addUser(user, "deji", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        
        
         user = new User("joe");
        user.setEmail("joe@gmail.com");
        user.setFirstName("Joe");
        user.setLastName("Adeoti");
        membershipService.addUser(user, "deji", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        
         user = new User("Spark");
        user.setEmail("spark@gmail.com");
        user.setFirstName("Spark");
        user.setLastName("Adeoti");
        membershipService.addUser(user, "deji", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
       
    }
}
