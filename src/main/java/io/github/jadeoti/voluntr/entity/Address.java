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
@Table(name = "address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
    , @NamedQuery(name = "Address.findById", query = "SELECT a FROM Address a WHERE a.id = :id")
    , @NamedQuery(name = "Address.findByCategory", query = "SELECT a FROM Address a WHERE a.category = :category")
    , @NamedQuery(name = "Address.findByDateCreated", query = "SELECT a FROM Address a WHERE a.dateCreated = :dateCreated")
    , @NamedQuery(name = "Address.findByDateUpdate", query = "SELECT a FROM Address a WHERE a.dateUpdate = :dateUpdate")
    , @NamedQuery(name = "Address.findByStreet", query = "SELECT a FROM Address a WHERE a.street = :street")
    })
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "category")
    private String category;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdate;
    
    
    @Column(name = "street")
    private String street;
    
    @ManyToOne
    @JoinColumn(name = "volunteer_id", referencedColumnName = "id")
    private Volunteer owner;
   
    public Address() {
    }

    public Address(Long id) {
        this.id = id;
    }

    public Address(Long id, String category, String street, Date dateCreate, Date dateUpdate) {
        this.id = id;
        this.category = category;
        this.dateCreated = dateCreate;
        this.dateUpdate = dateUpdate;
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

   

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "io.github.jadeoti.eduaid.entity.Address[ id=" + id + " ]";
    }
    
}
