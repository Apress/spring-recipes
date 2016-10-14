package com.apress.springrecipes.bank;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JdbcAccountDao extends SimpleJdbcDaoSupport implements AccountDao {

    public void createAccount(Account account) {
        String sql = "INSERT INTO ACCOUNT (ACCOUNT_NO, BALANCE) VALUES (?, ?)";
        getSimpleJdbcTemplate().update(
                sql, account.getAccountNo(), account.getBalance());
    }

    public void updateAccount(Account account) {
        String sql = "UPDATE ACCOUNT SET BALANCE = ? WHERE ACCOUNT_NO = ?";
        getSimpleJdbcTemplate().update(
                sql, account.getBalance(), account.getAccountNo());
    }

    public void removeAccount(Account account) {
        String sql = "DELETE FROM ACCOUNT WHERE ACCOUNT_NO = ?";
        getSimpleJdbcTemplate().update(sql, account.getAccountNo());
    }

    public Account findAccount(String accountNo) {
        String sql = "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = ?";
        double balance = getSimpleJdbcTemplate().queryForObject(
                sql, Double.class, accountNo);
        return new Account(accountNo, balance);
    }
}
