package com.apress.springrecipes.bank;

import com.apress.springrecipes.bank.AccountService;

import org.springframework.test.annotation.AbstractAnnotationAwareTransactionalTests;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;


public class AccountServiceJUnit38LegacyTests extends AbstractAnnotationAwareTransactionalTests {
    private static final String TEST_ACCOUNT_NO = "1234";
    protected AccountService accountService;

    public AccountServiceJUnit38LegacyTests() {
        setPopulateProtectedVariables(true);
    }

    protected String[] getConfigLocations() {
        return new String[] { "beans.xml" };
    }

    protected void onSetUpInTransaction() throws Exception {
        executeSqlScript("classpath:/bank.sql", true);
        getJdbcTemplate().update("INSERT INTO ACCOUNT (ACCOUNT_NO, BALANCE) VALUES (?, ?)", TEST_ACCOUNT_NO, 100);
    }

    @Timed(millis = 2000)
    public void testDeposit() {
        accountService.deposit(TEST_ACCOUNT_NO, 50);

        double balance = (Double) getJdbcTemplate().queryForObject("SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = ?", new Object[] { TEST_ACCOUNT_NO }, Double.class);
        assertEquals(balance, 150.0);
    }

    @Repeat(5)
    public void testWithDraw() {
        accountService.withdraw(TEST_ACCOUNT_NO, 50);

        double balance = (Double) getJdbcTemplate().queryForObject("SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = ?", new Object[] { TEST_ACCOUNT_NO }, Double.class);
        assertEquals(balance, 50.0);
    }
}
