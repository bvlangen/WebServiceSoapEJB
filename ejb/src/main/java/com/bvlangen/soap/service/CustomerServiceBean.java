package com.bvlangen.soap.service;

import com.bvlangen.soap.model.*;

import javax.ejb.Stateless;
import javax.jws.*;
import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

@Stateless
@WebService(name = "CustomerService",
            targetNamespace = "service")

// doc/lit wrapped binding style WSDL (because we need the method name in the SOAP request, see f.i. the
// methods that only have a customerid as parameter; which method to call then?)
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT,
             use = SOAPBinding.Use.LITERAL,
             parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@HandlerChain(file = "handler-chain.xml")
public class CustomerServiceBean implements CustomerService {

    @PersistenceContext
    private EntityManager em;

    @WebMethod(operationName = "AddCustomer")
    @WebResult(name="Customer")
    public Customer createCustomer(@WebParam(name="Name") String name) {
        Customer c = new Customer();
        c.setName(name);
        em.persist(c);
        return c;
    }

    @WebMethod(operationName = "GetCustomerDetails")
    @WebResult(name="Customer")
    public Customer getCustomer(@WebParam(name="CustomerID") int id) {
        return em.find(Customer.class, id);
    }

    @WebMethod(operationName = "AddCustomerEmailAddress")
    @WebResult(name="EmailAddress")
    public Email addEmail(@WebParam(name="CustomerID") int id, @WebParam(name="EmailAddress") String address, @WebParam(name="EmailType") EmailType type) {
        Customer c = getCustomer(id);
        if (c == null) {
            return null;
        }
        Email a = new Email();
        a.setAddress(address);
        a.setCustomer(c);
        a.setType(type);
        c.getEmailAddresses().add(a);
        em.persist(a);
        return a;
    }

    @WebMethod(operationName = "GetEmailAddress")
    @WebResult(name="Email")
    public Email getEmail(@WebParam(name="EmailPrimaryKey") EmailPK pk) {
        return em.find(Email.class, pk);
    }

    @WebMethod(operationName = "RemoveEmailAddress")
    @WebResult(name="Email")
    public Email removeEmail(@WebParam(name="EmailPrimaryKey") EmailPK pk) {
        Email e = getEmail(pk);
        if (e != null) {
            e.getCustomer().getEmailAddresses().remove(e);
            em.remove(e);
        }
        return e;
    }

    @WebMethod(operationName = "GetCustomerEmailAddresses")
    @WebResult(name="Email")
    public Set<Email> listEmail(@WebParam(name="CustomerID") int id) {
        return getCustomer(id).getEmailAddresses();
    }

    @WebMethod(operationName = "AddCustomerAddress")
    @WebResult(name="Address")
    public Address addAddress(@WebParam(name="CustomerID") int id, @WebParam(name="Street") String street, @WebParam(name="PostalCode") String postalCode, @WebParam(name="City") String city, @WebParam(name="AddressType") AddressType type) {
        Customer c = getCustomer(id);
        if (c == null) {
            return null;
        }
        Address a = new Address();
        a.setStreet(street);
        a.setPostalCode(postalCode);
        a.setCity(city);
        a.setCustomer(c);
        a.setType(type);
        c.getAddresses().add(a);
        em.persist(a);
        return a;
    }

    @WebMethod(operationName = "GetAddress")
    @WebResult(name="Address")
    public Address getAddress(@WebParam(name="AddressPrimaryKey") AddressPK pk) {
        return em.find(Address.class, pk);
    }

    @WebMethod(operationName = "RemoveAddress")
    @WebResult(name="Address")
    public Address removeAddress(@WebParam(name="AddressPrimaryKey") AddressPK pk) {
        Address a = getAddress(pk);
        if (a != null) {
            a.getCustomer().getAddresses().remove(a);
            em.remove(a);
        }
        return a;
    }

    @WebMethod(operationName = "GetCustomerAddresses")
    @WebResult(name="Address")
    public Set<Address> listAddresses(@WebParam(name="CustomerID") int id) {
        return getCustomer(id).getAddresses();
    }

}
