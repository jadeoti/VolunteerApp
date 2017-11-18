/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jadeoti.voluntr.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.picketlink.idm.jpa.model.sample.simple.AccountTypeEntity;

/**
 *
 * @author Morph-Deji
 */
@Entity
@Table(name="volunteer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Volunteer.findById", query = "SELECT v FROM Volunteer v WHERE v.id = :id"),
    @NamedQuery(name = "Volunteer.findAll", query = "SELECT v FROM Volunteer v"),
    @NamedQuery(name = "Volunteer.findActive", query = "SELECT v FROM Volunteer v WHERE v.active = TRUE"),
    @NamedQuery(name = "Volunteer.findInactive", query = "SELECT v FROM Volunteer v WHERE v.active = FALSE"),
})
public class Volunteer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(min = 0, max = 20)
    @Column(name= "title", length = 20, nullable = true)
    private String title;
   
    
    @Size(min = 1, max = 200, message = "Firstname cannot be empty.")
    @Column(name = "first_name", length = 200, nullable = false)
    private String firstName;
    
    @Size(min = 0, max = 200)
    @Column(name = "middle_name", length = 200, nullable = true)
    private String middleName;
    
    @Size(min = 1, max = 200, message = "Lastname cannot be empty.")
    @Column(name = "last_name", length = 200, nullable = false)
    private String lastName;
    
    @Size(min = 1, message = "Email Address cannot be empty.")    
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+",
            message = "Email format is invalid.")
    @Column(name = "email", length = 200, nullable = false)
    private String email;
    
    @Column(name = "want_news_letter")
    private boolean wantNewsletter;
    
    @Column(name = "active")
    private boolean active = true;
    
    @Size(min = 0, max = 100)
    @Column(name = "gender", length = 100, nullable = true)
    private String gender;
    
    @Column(name = "date_created")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();
    
    @Column(name = "date_updated")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateUpdated = new Date();    
    
    @Past
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "birthday")
    private Date birthday;
    
    @OneToMany(mappedBy = "owner")
    private List<Phone> phones;
    
    @OneToMany(mappedBy = "owner")
    private List<Address> addresses;
   
    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private AccountTypeEntity user;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isWantNewsletter() {
        return wantNewsletter;
    }

    public void setWantNewsletter(boolean wantNewsletter) {
        this.wantNewsletter = wantNewsletter;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public AccountTypeEntity getUser() {
        return user;
    }

    public void setUser(AccountTypeEntity user) {
        this.user = user;
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
        if (!(object instanceof Volunteer)) {
            return false;
        }
        Volunteer other = (Volunteer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "io.github.jadeoti.voluntr.data.entity.Volunteer[ id=" + id + " ]";
    }
    
}
