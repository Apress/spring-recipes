package com.apress.springrecipes.jbpm.jbpm4.customers;

import org.apache.commons.lang.exception.ExceptionUtils;

import org.apache.log4j.Logger;

import org.jbpm.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.io.ClassPathResource;

import org.springframework.orm.hibernate3.HibernateTemplate;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;


/**
 * This class uses jBPM to manage the lifecycle of a new {@link com.apress.springrecipes.jbpm.jbpm4.customers.Customer} using a workflow.
 *
 */
public class CustomerServiceImpl implements CustomerService {
    private static final String REGISTER_CUSTOMER_PROCESS_KEY = "RegisterCustomer";
    private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class);
    private List<String> processDefinitions;
    @Autowired
    private ProcessEngine processEngine;
    @Value("#{ processEngine.getRepositoryService() }")
    private RepositoryService repositoryService;
    @Value("#{ processEngine.getExecutionService() }")
    private ExecutionService executionService;
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @PostConstruct
    public void setupProcessDefinitions() {
        try {
            for (String processDefinition : processDefinitions) {
                NewDeployment deployment = repositoryService.createDeployment();
                deployment.addResourceFromUrl(new ClassPathResource(processDefinition).getURL());
                deployment.deploy();
            }
        } catch (IOException e) {
            logger.info("IOException occurred: " + ExceptionUtils.getFullStackTrace(e));
            throw new RuntimeException("An error occured while trying to deploy a process definition", e);
        }
    }

    public Customer createCustomer(String email, String passphrase, String firstName, String lastName) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPassphrase(passphrase);
        customer.setAuthorized(false);
        hibernateTemplate.saveOrUpdate(customer);

        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("customerId", customer.getId());

        ProcessInstance pi = executionService.startProcessInstanceByKey(REGISTER_CUSTOMER_PROCESS_KEY, vars, Long.toString(customer.getId()));

        System.out.println("pi.started " + pi.getProcessDefinitionId());

        return customer;
    }

    /**
     * Return a customer by the primary key
     */
    public Customer getCustomerById(Long id) {
        return (Customer) hibernateTemplate.get(Customer.class, id);
    }

    /**
     * There may be other use cases that aren't handled by this scenrio such as creation of system resources or
     * processing on data that happens on things given by the user at the time of the creation of the account.
     */
    @SuppressWarnings("unchecked")
    public void authorizeCustomer(Long customerId) {
        Customer customer = getCustomerById(customerId);
        customer.setAuthorized(true);

        String processInstanceKey = Long.toString(customerId);
        String hqlToFindProcessInstanceByProcessInstanceKey = "SELECT processInstance FROM ExecutionImpl AS processInstance WHERE  processInstance.key = :processInstanceKey";
        List<Execution> executions = hibernateTemplate.findByNamedParam(hqlToFindProcessInstanceByProcessInstanceKey, new String[] { "processInstanceKey" }, new Object[] { processInstanceKey });
        System.out.println("executions: " + executions);

        for (Execution execution : executions) {
            Execution subExecution = execution.findActiveExecutionIn("confirm-receipt-of-verification-email");
            executionService.signalExecutionById(subExecution.getId());
        }

        hibernateTemplate.saveOrUpdate(customer);
    }

    /**
     * There may be other use cases that aren't handled by this method where it might be appropriate to spin off a
     * workflow. Examples include revocation of specialized service accounts (FTP, e-mail, whatever), file shares, etc.
     */
    public void deauthorizeCustomer(Long customerId) {
        Customer customer = getCustomerById(customerId);
        customer.setAuthorized(false);
        hibernateTemplate.saveOrUpdate(customer);
    }

    public void sendCustomerVerificationEmail(Long customerId) {
        System.out.println("Sending customer verification email to " + customerId);
    }

    /**
     * A simple mutator to facilitate configuration.
     *
     * @param pd
     */
    public void setProcessDefinitions(List<String> pd) {
        this.processDefinitions = pd;
    }

    public void sendWelcomeEmail(Long customerId) {
        System.out.println("Sending customer welcome email to " + customerId);
    }
}
