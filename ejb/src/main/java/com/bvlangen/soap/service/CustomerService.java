package com.bvlangen.soap.service;

import com.bvlangen.soap.model.*;

import javax.ejb.Remote;
import java.util.Set;

public interface CustomerService {
    Customer createCustomer(String name);
    Customer getCustomer(int id);
    Email addEmail(int id, String address, EmailType type);
    Email getEmail(EmailPK pk);
    Email removeEmail(EmailPK pk);
    Set<Email> listEmail(int id);
    Address addAddress(int id, String street, String postalCode, String city, AddressType type);
    Address getAddress(AddressPK pk);
    Address removeAddress(AddressPK pk);
    Set<Address> listAddresses(int id);
}
