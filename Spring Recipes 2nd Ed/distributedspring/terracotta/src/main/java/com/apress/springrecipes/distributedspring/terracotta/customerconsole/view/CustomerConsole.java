package com.apress.springrecipes.distributedspring.terracotta.customerconsole.view;

import com.apress.springrecipes.distributedspring.terracotta.customerconsole.entity.Customer;
import com.apress.springrecipes.distributedspring.terracotta.customerconsole.service.CustomerService;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.text.DateFormat;
import java.text.ParseException;

import java.util.Date;

import javax.swing.*;


public class CustomerConsole {
    private CustomerService customerService;

    private void log(String msg) {
        System.out.println(msg);
    }

    private void list() {
        for (Customer customer : customerService.getAllCustomers()) {
            log(customer.toString());
        }

        log(SystemUtils.LINE_SEPARATOR);
    }

    private void create(String customerCreationString) {
        String cmd = StringUtils.defaultString(customerCreationString).trim();
        String[] parts = cmd.split(" ");
        String firstName = parts[1];
        String lastName = parts[2];
        Date date = null;

        try {
            date = DateFormat.getDateInstance(DateFormat.SHORT).parse(parts[3]);
        } catch (ParseException e) {
            log(ExceptionUtils.getFullStackTrace(e));
        }

        customerService.createCustomer(firstName, lastName, date);
        list();
    }

    private void delete(String c) {
        log("delete:" + c);

        String id = StringUtils.defaultString(c).trim().split(" ")[1];
        customerService.removeCustomer(id);
        list();
    }

    private void update(String stringToUpdate) {
        String[] parts = StringUtils.defaultString(stringToUpdate).trim().split(" ");
        String idOfCustomerAsPrintedOnConsole = parts[1];
        String firstName = parts[2];
        String lastName = parts[3];
        Date date = null;

        try {
            date = DateFormat.getDateInstance(DateFormat.SHORT).parse(parts[4]);
        } catch (ParseException e) {
            log(ExceptionUtils.getFullStackTrace(e));
        }

        customerService.updateCustomer(idOfCustomerAsPrintedOnConsole, firstName, lastName, date);
        list();
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void handleNextCommand(String prompt) {
        System.out.println(prompt);

        String nextLine = JOptionPane.showInputDialog(null, prompt);

        if (StringUtils.isEmpty(nextLine)) {
            System.exit(1);

            return;
        }

        log(nextLine);

        if ((StringUtils.trim(nextLine).toUpperCase()).startsWith(Commands.UPDATE.name())) {
            update(nextLine);

            return;
        }

        if ((StringUtils.trim(nextLine).toUpperCase()).startsWith(Commands.DELETE.name())) {
            delete(nextLine);

            return;
        }

        if ((StringUtils.trim(nextLine).toUpperCase()).startsWith(Commands.CREATE.name())) {
            create(nextLine);

            return;
        }

        if ((StringUtils.trim(nextLine).toUpperCase()).startsWith(Commands.LIST.name())) {
            list();

            return;
        }

        System.exit(1);
    }
    enum Commands {LIST, UPDATE, DELETE, CREATE;
    }
}
