package com.bvlangen.soap.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@Entity
@Table(name = "email")
@IdClass(EmailPK.class)
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(name = "Email", namespace = "email")
public class Email implements Serializable {

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
    private String address;

    public Email() {
    }

    public String getAddress() {
        return address;
    }

    @XmlElement(name = "EmailAddress")
    public void setAddress(String address) {
        this.address = address;
    }

    @XmlTransient // bidirectional relation causes a loop for XML serialization; break it at this side.
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customerId = customer.getId();
        this.customer = customer;
    }

    public EmailType getType() {
        return EmailType.values()[type];
    }

    @XmlElement(name = "EmailType")
    public void setType(EmailType type) {
        this.type = type.ordinal();
    }
}
