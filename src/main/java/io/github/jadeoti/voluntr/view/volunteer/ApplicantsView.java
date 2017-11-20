/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.view.volunteer;

import io.github.jadeoti.voluntr.entity.Applicant;
import io.github.jadeoti.voluntr.entity.ApplicationDecision;
import io.github.jadeoti.voluntr.entity.Volunteer;
import io.github.jadeoti.voluntr.service.MembershipService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Morph-Deji
 */
@Named(value = "applicantsView")
@ViewScoped
public class ApplicantsView implements Serializable{

    
    private Applicant applicant = new Applicant();

    private List<Applicant> applicants;

    @Inject
    private MembershipService membershipService;
    /**
     * Creates a new instance of VolunteersView
     */
    public ApplicantsView() {
    }

    @PostConstruct
    public void init(){
        // all applicants
        applicants = membershipService.getApplicants(null);
    }
    
    public Applicant getApplicant() {
        return applicant;
    }
    
    public void show(Applicant applicant){
        this.applicant = applicant;
    }
    
    public void decide(ApplicationDecision decision){
        membershipService.decide(applicant, decision);
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }
    

    public List<Applicant> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<Applicant> applicants) {
        this.applicants = applicants;
    }
    
    
    
}
