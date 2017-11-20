/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.util;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.picketlink.annotations.PicketLink;

/**
 *
 * @author Morph-Deji
 */
public class Injection {
    
    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint){
       return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
    
    @Produces
    @RequestScoped
    public FacesContext produceFacesContext() {
        return FacesContext.getCurrentInstance();
    }
    
       
    /**
     * The EntityManager.
     */
    @PersistenceContext(unitName = "io.gitub.jadeoti.voluntrPU")
    private EntityManager em;

    /**
     * Produces the EntityManager.
     *
     * @return the EntityManager.
     */
    @Produces
    public EntityManager producePicketLinkEntityManager() {
        return em;
    }
}
