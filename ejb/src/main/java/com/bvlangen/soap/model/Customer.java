package com.bvlangen.soap.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer", namespace = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(name = "CustomerID", nillable = false)
    private int id;

    @Column(length = 100)
    @XmlElement(name = "Name")
    private String name;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @XmlElement(name = "EmailAddresses")
    private Set<Email> emailAddresses;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @XmlElement(name = "Addresses")
    private Set<Address> addresses;

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Email> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(Set<Email> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

}
