/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.service;

import io.github.jadeoti.voluntr.entity.Volunteer;
import io.github.jadeoti.voluntr.persistence.MembershipPersistenceService;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.picketlink.Identity;
import org.picketlink.common.util.StringUtil;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.User;

/**
 *
 * @author Morph-Deji
 */
@Stateless
public class MembershipService {

    @EJB
    private MembershipPersistenceService persistenceService;
    
    @Inject
    private PartitionManager partitionManager;
    
    @Inject
    private Identity identity;
    
    //@Inject
    //private Logger log;
    

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
    
   public boolean usernameExists(String username){
       IdentityManager idManager = partitionManager.createIdentityManager();
       return (null != BasicModel.getUser(idManager, username));       
   }
    
   public void addUser(User user, String password){
       IdentityManager idManager = partitionManager.createIdentityManager();
       if(user != null && password != null){
           idManager.add(user);
           idManager.updateCredential(user, new Password(password));
       }else{
           //log.warning("Invalid paramaters. One user or password is null");
       }

   }
   
   public void updateUser(User user, String password){
       IdentityManager idManager = partitionManager.createIdentityManager();
       User savedUser = BasicModel.getUser(idManager, user.getLoginName());
       
       savedUser.setEmail(user.getEmail());
       savedUser.setFirstName(user.getFirstName());
       savedUser.setLastName(user.getLastName());
       idManager.update(savedUser);
       if(!StringUtil.isNullOrEmpty(password)){
           idManager.updateCredential(savedUser, new Password(password));
       }       
   }
   
   
    public void remove(User user) {
        IdentityManager identityManager = partitionManager
                .createIdentityManager();
        User savedUser = BasicModel.getUser(identityManager,
                user.getLoginName());
        identityManager.remove(savedUser);
    }
    
    
    public boolean validName(String name){
        return !StringUtil.isNullOrEmpty(name);
    }
    
    public boolean validEmail(String email){
        if (StringUtil.isNullOrEmpty(email)){
            return false;
        }else if(!email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")){
            return false;
        }else {
            return true;
        }
    }
    
}
