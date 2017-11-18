/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.setup;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
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
    
    @PostConstruct
    public void init(){
        addUser();
        addRoles();
    }
    
    public void addUser(){        
        System.out.println("::::::::: Init running");
        IdentityManager idManager = this.partitionManager.createIdentityManager();
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
    
    public void addRoles(){
       
    }
}
