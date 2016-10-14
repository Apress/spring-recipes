package com.apress.springrecipes.distributedspring.terracotta.customerconsole.service;

import com.apress.springrecipes.distributedspring.terracotta.customerconsole.entity.Customer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class CustomerServiceImpl implements CustomerService {
    private volatile Set<Customer> customers;

    public CustomerServiceImpl() {
        customers = Collections.synchronizedSet(new HashSet());
    }

    public Customer updateCustomer(String id, String firstName, String lastName, Date birthdate) {
        Customer customer;
        synchronized (customers) {
            customer = getCustomerById(id);
            customer.setBirthday(birthdate);
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            removeCustomer(id);
            customers.add(customer);
        }
        return customer;
    }

    public Collection<Customer> getAllCustomers() {
        return (customers);
    }

    public Customer removeCustomer(String id) {
        Customer customerToRemove;

        synchronized (customers) {
            customerToRemove = getCustomerById(id);

            if (null != customerToRemove) {
                customers.remove(customerToRemove);
            }
        }

        return customerToRemove;
    }

    public Customer getCustomerById(final String id) {
        return (Customer) CollectionUtils.find(customers,
                                               new Predicate() {
                                                   public boolean evaluate(Object o) {
                                                       Customer customer = (Customer) o;

                                                       String newId = StringUtils.defaultString(id).trim().toLowerCase();
                                                       String idToCompare = StringUtils.defaultString(customer.getId()).trim().toLowerCase();

                                                       return newId.equals(idToCompare);
                                                   }
                                               });
    }

    public Customer createCustomer(final String firstName, final String lastName, final Date birthdate) {
        synchronized (customers) {
            final Customer newCustomer = new Customer(firstName, lastName, birthdate);

            if (!customers.contains(newCustomer)) {
                customers.add(newCustomer);

                return newCustomer;
            }
            else {
                return (Customer) CollectionUtils.find(customers,
                                                       new Predicate() {
                                                           public boolean evaluate(Object o) {
                                                               Customer customer = (Customer) o;

                                                               return customer.equals(newCustomer);
                                                           }
                                                       });
            }
        }
    }
}
