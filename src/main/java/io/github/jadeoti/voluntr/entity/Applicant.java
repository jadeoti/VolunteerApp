/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Morph-Deji
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Applicant.findById", query = "SELECT a FROM Applicant a WHERE a.id = :id"),
    @NamedQuery(name = "Applicant.findAll", query = "SELECT a FROM Applicant a"),
    @NamedQuery(name = "Applicant.findApplicantByDecision", query = "SELECT a FROM Applicant a WHERE a.decision = :decision"),
    })
public class Applicant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @Size(min = 1, max = 200, message = "Firstname cannot be empty.")
    @Column(name = "first_name", length = 200, nullable = false)
    private String firstName;

    @Size(min = 0, max = 200)
    @Column(name = "middle_name", length = 200, nullable = true)
    private String middleName;

    @Size(min = 1, max = 200, message = "Lastname cannot be empty.")
    @Column(name = "last_name", length = 200, nullable = false)
    private String lastName;

    @Lob
    @Size(min = 1, message = "Essay cannot be empty")
    @Column(name = "essay", nullable = false)
    private String essay;
    
    @Size(min = 1, message = "Email Address cannot be empty.")
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+",
            message = "Email format is invalid.")
    @Column(name = "email", length = 200, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private ApplicationDecision decision;
    
    @Column(name="username", unique = true)
    private String username;
    
    @Column(name = "date_created")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();

    @Column(name = "date_updated")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateUpdated = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Applicant)) {
            return false;
        }
        Applicant other = (Applicant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "io.github.jadeoti.voluntr.entity.Applicant[ id=" + id + " ]";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEssay() {
        return essay;
    }

    public void setEssay(String essay) {
        this.essay = essay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ApplicationDecision getDecision() {
        return decision;
    }

    public void setDecision(ApplicationDecision decision) {
        this.decision = decision;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    
    
}
