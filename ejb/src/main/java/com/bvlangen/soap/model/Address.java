package com.bvlangen.soap.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
@IdClass(AddressPK.class)
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(name = "Address", namespace = "address")
public class Address implements Serializable {
    @Id
    @Column(name = "customer_id")
    private int customerId;

    @ManyToOne
    // this field must not be written to the database
    @JoinColumn(insertable = false, updatable = false)
    private Customer customer;
    @Id
    private int type;
    @Column(length = 100)
    private String street;
    @Column(name = "postal_code", length = 7)
    private String postalCode;
    @Column(length = 100)
    private String city;

    public Address() {
    }

    @XmlTransient // bidirectional relation causes a loop for XML serialization; break it at this side.
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customerId = customer.getId();
        this.customer = customer;
    }

    public AddressType getType() {
        return AddressType.values()[type];
    }

    @XmlElement(name = "AddressType")
    public void setType(AddressType type) {
        this.type = type.ordinal();
    }

    public String getStreet() {
        return street;
    }

    @XmlElement(name = "Street")
    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @XmlElement(name = "PostalCode")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    @XmlElement(name = "City")
    public void setCity(String city) {
        this.city = city;
    }

}