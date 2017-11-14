/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Morph-Deji
 */
@Entity
@Table(name = "phone")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Phone.findAll", query = "SELECT p FROM Phone p")
    , @NamedQuery(name = "Phone.findById", query = "SELECT p FROM Phone p WHERE p.id = :id")
    , @NamedQuery(name = "Phone.category", query = "SELECT p FROM Phone p WHERE p.category = :category")
    , @NamedQuery(name = "Phone.findByPhoneNumber", query = "SELECT p FROM Phone p WHERE p.phoneNumber = :phoneNumber")})
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
   
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "phone_number")
    private String phoneNumber;
    
  
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "category")
    private String category;
  
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date dateCreated;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_updated")
    private Date dateUpdated;
    
    @ManyToOne
    @JoinColumn(name = "volunteer_id", referencedColumnName = "id")
    private Volunteer owner;

    public Phone() {
    }

    public Phone(Long id) {
        this.id = id;
    }

    public Phone(Long id, String phoneNumber, String phoneCategory) {
        this.id = id;
        this.category = phoneCategory;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }   

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Phone)) {
            return false;
        }
        Phone other = (Phone) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "io.github.jadeoti.eduaid.entity.Phone[ id=" + id + " ]";
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Volunteer getOwner() {
        return owner;
    }

    public void setOwner(Volunteer owner) {
        this.owner = owner;
    }

}
